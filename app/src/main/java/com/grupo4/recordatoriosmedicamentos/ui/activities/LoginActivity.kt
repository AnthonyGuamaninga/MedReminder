package com.grupo4.recordatoriosmedicamentos.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.grupo4.recordatoriosmedicamentos.databinding.ActivityLoginBinding
import com.grupo4.recordatoriosmedicamentos.logic.usercases.local.LoginUseCase
import com.grupo4.recordatoriosmedicamentos.core.Constants
import com.grupo4.recordatoriosmedicamentos.ui.core.My_Application

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initControls()
    }

    fun initControls() {
        binding.btnAccess.setOnClickListener {
            val check = LoginUseCase(
                My_Application.getConnectionDB()!!
            ).checkUserandPassword(
                binding.txtInputUser.text.toString(),
                binding.txtInputPassword.text.toString()
            )

            if (check > 0) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(Constants.TAG, check)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.btnAccess,
                    "Usuario o contraseña incorrecto",
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.txtInputUser.text?.clear()
                binding.txtInputPassword.text?.clear()
            }
        }

    }
}