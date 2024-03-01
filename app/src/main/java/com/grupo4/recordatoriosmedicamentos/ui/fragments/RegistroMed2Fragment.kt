package com.grupo4.recordatoriosmedicamentos.ui.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.databinding.FragmentRegistroMedBinding
import com.grupo4.recordatoriosmedicamentos.logic.entities.RecetaSingleton
import com.grupo4.recordatoriosmedicamentos.ui.viewModels.MedicamentoRegistroViewModel
import java.time.LocalDateTime


class RegistroMed2Fragment : Fragment() {
    private lateinit var binding : FragmentRegistroMedBinding
    private val medicamentoRegistroViewModel : MedicamentoRegistroViewModel by viewModels()
    private val fecha= LocalDateTime.now()
    private val recetaSingleton=RecetaSingleton.instance

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

            recetaSingleton.temporal.dosis=binding.edtImputDosis.text.toString()
            recetaSingleton.temporal.frecuencia=binding.edtInputFrecuencia.text.toString()
            recetaSingleton.temporal.hora_inicio=binding.edtTextImputHoraInicio.toString()
            recetaSingleton.temporal.f_inicio=binding.edtTextImputFechaInicio.text.toString()
            recetaSingleton.temporal.caducidad=binding.edtInputCaducidad.text.toString()
            findNavController().navigate(
                RegistroMed2FragmentDirections.actionRegistroMedFragmentToRegistroMed3Fragment()
            )
        }
        binding.edtTextImputFechaInicio.setOnClickListener {
            Log.d("Tiempo",fecha.toString())
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
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { view, year1, monthOfYear, dayOfMonth ->
                val dateChoice = (dayOfMonth.toString() + "-" +(monthOfYear+1).toString() + "-" + year1.toString())
                binding.edtTextImputFechaInicio.setText(dateChoice)
                //temp = dateChoice
            }, fecha.year, fecha.monthValue-1, fecha.dayOfMonth
        )
        datePickerDialog.show()
    }

    private fun timePicker(){

        val timePicker = TimePickerDialog(
            requireContext(),
            {view, hour,minute ->
                var hora:String
                var minuto:String
                if (hour<10) hora="0"+hour.toString() else hora=hour.toString()
                if (minute<10) minuto="0"+minute.toString() else minuto=minute.toString()
                val timeChoice = (hora+":"+minuto)
                binding.edtTextImputHoraInicio.setText(timeChoice)
            }, fecha.hour,fecha.minute,false
        )
        timePicker.show()
    }


    fun hideKeyboard(context: Context, view: View) {
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}