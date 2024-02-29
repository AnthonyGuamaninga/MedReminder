package com.grupo4.recordatoriosmedicamentos.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.MedInfo
import com.grupo4.recordatoriosmedicamentos.databinding.FragmentRegsitroMed1Binding

class RegsitroMed1Fragment : Fragment() {

    private lateinit var binding:FragmentRegsitroMed1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRegsitroMed1Binding.bind(inflater.inflate(R.layout.fragment_regsitro_med1, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnSiguiente.setOnClickListener {
            findNavController().navigate(RegsitroMed1FragmentDirections.actionRegsitroMed1FragmentToRegistroMedFragment(medInfo = binding.registrarMedicina.text.toString()))
        }
    }

}