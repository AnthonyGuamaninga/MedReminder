package com.grupo4.recordatoriosmedicamentos.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.core.Constants
import com.grupo4.recordatoriosmedicamentos.data.network.entities.fda.Product
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.MedInfo
import com.grupo4.recordatoriosmedicamentos.databinding.FragmentRegsitroMed1Binding
import com.grupo4.recordatoriosmedicamentos.logic.entities.FullInfoDrugsLG
import com.grupo4.recordatoriosmedicamentos.logic.usercases.fda.FdaGetResultDrugsUserCase
import com.grupo4.recordatoriosmedicamentos.ui.adapter.DrugsAdapterDiffUtil
import com.grupo4.recordatoriosmedicamentos.ui.adapter.ProductAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.grupo4.recordatoriosmedicamentos.logic.entities.RecetaSingleton


class RegsitroMed1Fragment : Fragment() {

    private lateinit var binding: FragmentRegsitroMed1Binding
    private val recetaSingleton= RecetaSingleton.instance


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegsitroMed1Binding.bind(
            inflater.inflate(
                R.layout.fragment_regsitro_med1,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        //loadDataRecyclerView()
        val autoCompleteTextView = binding.autoCompletetxt
        ProductAdapter(requireActivity(), Constants.products).also {
            binding.autoCompletetxt.setAdapter(it)
        }
    }

    private fun initListeners() {
        binding.btnSiguiente.setOnClickListener {

            val ultimo=recetaSingleton.listaMed.size
            recetaSingleton.temporal.medId=binding.autoCompletetxt.text.toString()
            findNavController().navigate(
                RegsitroMed1FragmentDirections.actionRegsitroMed1FragmentToRegistroMedFragment(
                    medInfo = binding.autoCompletetxt.text.toString()
                )
            )
        }
    }

}