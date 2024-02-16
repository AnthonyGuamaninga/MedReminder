package com.grupo4.recordatoriosmedicamentos.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo4.recordatoriosmedicamentos.databinding.ActivityLoginBinding
import com.grupo4.recordatoriosmedicamentos.logic.usercases.local.LoginUseCase
import com.grupo4.recordatoriosmedicamentos.core.Constants
import com.grupo4.recordatoriosmedicamentos.ui.core.My_Application

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        initControls()
    }

    override fun onStart() {
        super.onStart()

        val currentUser= auth.currentUser
        if(currentUser != null){
            startActivity(Intent(this,MainActivity::class.java))
        }else{

        }
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
        binding.linkAccountCreate.setOnClickListener {
            createNewUser(binding.txtInputUser.text.toString(), binding.txtInputPassword.text.toString())
        }
        binding.btnAccess.setOnClickListener {
            SingInUsers(binding.txtInputUser.text.toString(), binding.txtInputPassword.text.toString())
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