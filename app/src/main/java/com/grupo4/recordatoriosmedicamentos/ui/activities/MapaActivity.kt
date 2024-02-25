package com.grupo4.recordatoriosmedicamentos.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grupo4.recordatoriosmedicamentos.databinding.ActivityMapaBinding

class MapaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMapaBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}