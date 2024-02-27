package com.grupo4.recordatoriosmedicamentos.ui.activities

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.databinding.ActivityLoginBinding
import com.grupo4.recordatoriosmedicamentos.ui.viewModels.LoginViewModel
import java.util.concurrent.Executor

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    // biometric
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var dialog: AlertDialog

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initListeners()
        initObservables()
        autenticationVariables()
        loginViewModel.checkBiometric(this)
        auth = Firebase.auth
    }

    fun initListeners() {
        binding.btnAccess.setOnClickListener {
            var user=binding.txtInputUser.text.toString()
            var password=binding.txtInputPassword.text.toString()
            if (user.isBlank() && password.isBlank()) {
                dialog =
                    AlertDialog.Builder(this).setTitle("Advertencia").setMessage("campos vacios")
                        .setPositiveButton("Ok") { _, _ ->
                            this
                        }.create()
                dialog.show()
            } else {
                loginViewModel.singInUser(
                    binding.txtInputUser.text.toString(),
                    binding.txtInputPassword.text.toString()
                )
            }


        }
        binding.imgfinger.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
        binding.linkAccountCreate.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegistroActivity::class.java))

        }
    }


    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, VistaPrincipalActivity::class.java)
            intent.putExtra("usuarioID", currentUser.uid)
            startActivity(intent)
        }

    }


    private fun autenticationVariables() {
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    startActivity(Intent(this@LoginActivity, VistaPrincipalActivity::class.java))
                }

            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
            .build()
    }

    private fun initObservables() {
        loginViewModel.user.observe(this) {
            startActivity(Intent(this, VistaPrincipalActivity::class.java))
        }
        loginViewModel.error.observe(this) {
            Snackbar.make(
                this,
                binding.txtInputUser,
                it,
                Snackbar.LENGTH_LONG
            ).show()
        }
        loginViewModel.resultCheckBiometric.observe(this) { code ->
            when (code) {
                BiometricManager.BIOMETRIC_SUCCESS -> {
                    binding.imgfinger.visibility = View.VISIBLE
                    binding.txtFinger.visibility = View.VISIBLE
                }

                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                    binding.txtFinger.text = getString(R.string.biometric_no_hardware)
                }

                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                    Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
                }

                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                        putExtra(
                            Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                            BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
                        )
                    }
                    startActivityForResult(enrollIntent, 100)
                }
            }
        }
    }


}