package com.grupo4.recordatoriosmedicamentos.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.grupo4.recordatoriosmedicamentos.R

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import com.grupo4.recordatoriosmedicamentos.databinding.ActivityLoginBinding
import com.grupo4.recordatoriosmedicamentos.logic.usercases.local.LoginUseCase
import com.grupo4.recordatoriosmedicamentos.core.Constants
import com.grupo4.recordatoriosmedicamentos.ui.core.My_Application
import com.grupo4.recordatoriosmedicamentos.ui.viewModels.LoginViewModel
import java.util.concurrent.Executor

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    // biometric
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private val loginViewModel : LoginViewModel by viewModels()

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
            signInUsers(
                binding.txtInputUser.text.toString(),
                binding.txtInputPassword.text.toString()
            )
        }
        binding.imgfinger.setOnClickListener{
            biometricPrompt.authenticate(promptInfo)
        }
        binding.linkAccountCreate.setOnClickListener {
            createNewUser(binding.txtInputUser.text.toString(), binding.txtInputPassword.text.toString())
        }
    }
    
    override fun onStart() {
        super.onStart()

        val currentUser= auth.currentUser
        if(currentUser != null){
            startActivity(Intent(this,MainActivity::class.java))
        }else{

        }
    }


    private fun autenticationVariables(){
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }

            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
            .build()
    }

    private fun initObservables(){
        loginViewModel.resultCheckBiometric.observe(this){code ->
            when(code){
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
        
      

    private fun createNewUser(user:String, password:String){
        auth.createUserWithEmailAndPassword(user, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = auth.currentUser
                    Snackbar.make(this, binding.txtInputUser, "createUserWhitEmail: success", Snackbar.LENGTH_SHORT).show()
                    binding.txtInputUser.text!!.clear()
                    binding.txtInputPassword.text!!.clear()
                } else {
                    // If sign in fails, display a message to the user.
                    Snackbar.make(this, binding.txtInputUser, "createUserWhitEmail: error", Snackbar.LENGTH_SHORT).show()
                    Log.d("TAG", task.exception!!.stackTraceToString())
                }
            }
    }

    private fun SingInUsers(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user=auth.currentUser
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Snackbar.make(this, binding.txtInputUser, "createUserWhitEmail: error", Snackbar.LENGTH_SHORT).show()
                    Log.d("TAG", task.exception!!.stackTraceToString())
                }
            }
    }


}