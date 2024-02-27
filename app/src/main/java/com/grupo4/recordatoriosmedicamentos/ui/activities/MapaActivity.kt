package com.grupo4.recordatoriosmedicamentos.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grupo4.recordatoriosmedicamentos.databinding.ActivityMapaBinding
import com.grupo4.recordatoriosmedicamentos.ui.fragments.MapsFragment

class MapaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMapaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.frmContainer.id, MapsFragment())
        transaction.commit()
    }
}