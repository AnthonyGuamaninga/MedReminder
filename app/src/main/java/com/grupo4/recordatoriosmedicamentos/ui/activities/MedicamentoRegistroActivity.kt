package com.grupo4.recordatoriosmedicamentos.ui.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.databinding.ActivityMedicamentoRegistroBinding
import com.grupo4.recordatoriosmedicamentos.ui.viewModels.MedicamentoRegistroViewModel

class MedicamentoRegistroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMedicamentoRegistroBinding
    private val medicamentoRegistroViewModel : MedicamentoRegistroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMedicamentoRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        initObservables()

    }

    private fun initListeners() {
        binding.btnRegistrar.setOnClickListener {
            medicamentoRegistroViewModel.createReceta(
                binding.edtImputDosis.text.toString(),
                binding.edtImputIndicaciones.text.toString(),
                binding.edtInputFrecuencia.text.toString(),
                binding.edtTextImputFechaInicio.text.toString(),
                binding.edtInputCaducidad.text.toString(),
                binding.edtInputObservaciones.text.toString(),
                "5801206-7"
            )
        }
        binding.edtTextImputFechaInicio.setOnClickListener {
            datePicker()
        }
        binding.edtTextImputFechaInicio.onFocusChangeListener= View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(this, binding.edtTextImputFechaInicio) // view es la vista actual
            }
        }


    }

    private fun initObservables() {
        medicamentoRegistroViewModel.receta.observe(this){
            startActivity(Intent(this, VistaPrincipalActivity::class.java))
        }
    }

    private fun datePicker(){
        // Valores por defecto del DatePicker
        val year = 2000
        val month = 0
        val day = 1

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year1, monthOfYear, dayOfMonth ->
                val dateChoice = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year1)
                binding.edtTextImputFechaInicio.setText(dateChoice)
                //temp = dateChoice
            }, year, month, day
        )
        datePickerDialog.show()
    }


    fun hideKeyboard(context: Context, view: View) {
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}