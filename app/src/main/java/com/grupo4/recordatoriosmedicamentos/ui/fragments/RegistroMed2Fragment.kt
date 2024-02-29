package com.grupo4.recordatoriosmedicamentos.ui.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.databinding.FragmentRegistroMedBinding
import com.grupo4.recordatoriosmedicamentos.ui.viewModels.MedicamentoRegistroViewModel
import java.time.LocalDateTime


class RegistroMed2Fragment : Fragment() {
    private lateinit var binding : FragmentRegistroMedBinding
    private val medicamentoRegistroViewModel : MedicamentoRegistroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=
            FragmentRegistroMedBinding.bind(inflater.inflate(R.layout.fragment_registro_med,container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservables()
    }

    private fun initListeners() {
        binding.btnRegistrar.setOnClickListener {
            medicamentoRegistroViewModel.createReceta(
                "Receta1UserUnknown",
                binding.edtImputDosis.text.toString(),
                binding.edtInputFrecuencia.text.toString(),
                binding.edtTextImputFechaInicio.text.toString(),
                "14:00",
                binding.edtInputCaducidad.text.toString(),
                "","",
                /*binding.edtImputIndicaciones.text.toString(),
                binding.edtInputObservaciones.text.toString(),*/
                "5801206-7"
            )
        }
        binding.edtTextImputFechaInicio.setOnClickListener {
            datePicker()
            hideKeyboard(requireContext(), binding.txtInputLayoutFechaInicio)
        }
        binding.edtTextImputHoraInicio.setOnClickListener{
            timePicker()
            hideKeyboard(requireContext(), binding.txtInputLayoutHoraInicio)
        }
    }

    private fun initObservables() {
        medicamentoRegistroViewModel.receta.observe(viewLifecycleOwner){
            //startActivity()
        }
    }

    private fun datePicker(){
        val fecha= LocalDateTime.now()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { view, year1, monthOfYear, dayOfMonth ->
                val dateChoice = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year1)
                binding.edtTextImputFechaInicio.setText(dateChoice)
                //temp = dateChoice
            }, fecha.year, fecha.monthValue, fecha.dayOfYear
        )
        datePickerDialog.show()
    }

    private fun timePicker(){
        val hora=12
        val minutos=0
        val timePicker = TimePickerDialog(
            requireContext(),
            {view, hour,minunte ->
                val timeChoice = (hour.toString()+":"+minutos)
                binding.edtTextImputHoraInicio.setText(timeChoice)
            }, hora,minutos,false
        )
        timePicker.show()
    }


    fun hideKeyboard(context: Context, view: View) {
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}