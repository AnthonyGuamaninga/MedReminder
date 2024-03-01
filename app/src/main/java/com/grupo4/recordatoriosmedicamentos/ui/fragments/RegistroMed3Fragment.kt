package com.grupo4.recordatoriosmedicamentos.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.MedTemp
import com.grupo4.recordatoriosmedicamentos.databinding.FragmentRegistroMed3Binding
import com.grupo4.recordatoriosmedicamentos.logic.entities.RecetaSingleton

class RegistroMed3Fragment : Fragment() {

    private lateinit var binding: FragmentRegistroMed3Binding
    private val recetaSingleton= RecetaSingleton.instance
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRegistroMed3Binding.bind(inflater.inflate(R.layout.fragment_registro_med3, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        initListeners()
    }

    private fun initListeners() {
        binding.btnRegistrar.setOnClickListener {
            recetaSingleton.temporal.indicaciones=binding.edtImputDosis.text.toString()
            recetaSingleton.temporal.observaciones=binding.edtInputObservaciones.text.toString()
            recetaSingleton.temporal.id=recetaSingleton.temporal.id+"-"+recetaSingleton.listaMed.size.toString()
            recetaSingleton.agregarMed(recetaSingleton.temporal)
            recetaSingleton.temporal= MedTemp()
            findNavController().navigate(
                RegistroMed3FragmentDirections.actionGlobalNuevaRecetaFragment(userInfo = auth.currentUser?.uid.toString())
            )
        }
    }


}