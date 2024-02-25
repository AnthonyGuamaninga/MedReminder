package com.grupo4.recordatoriosmedicamentos.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.grupo4.recordatoriosmedicamentos.databinding.ActivityRegistroBinding
import com.grupo4.recordatoriosmedicamentos.ui.viewModels.RegistroViewModel

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var auth: FirebaseAuth
    private val registroViewModel: RegistroViewModel by viewModels()
    private lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        initObservables()
    }

    fun initListeners() {
        binding.btnRegistrar.setOnClickListener {
            var correo=binding.txtCorreo.text.toString()
            var contra=binding.txtPassword.text.toString()
            var nombre=binding.txtNombre.text.toString()

            if(correo.isBlank()||contra.isBlank()||nombre.isBlank()){
                dialog =
                    AlertDialog.Builder(this).setTitle("Advertencia").setMessage("campos vacios")
                        .setPositiveButton("Ok") { _, _ ->
                            this
                        }.create()
                dialog.show()
            }else{
                registroViewModel.createUser(
                    binding.txtCorreo.text.toString(),
                    binding.txtPassword.text.toString(),
                    binding.txtNombre.text.toString()
                )
            }

        }
    }


    private fun initObservables() {
        registroViewModel.user.observe(this) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        registroViewModel.error.observe(this) {
            Snackbar.make(
                this,
                binding.txtCorreo,
                "createUserWhitEmail: error",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }


}