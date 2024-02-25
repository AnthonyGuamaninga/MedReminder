package com.grupo4.recordatoriosmedicamentos.ui.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import com.grupo4.recordatoriosmedicamentos.databinding.ActivityMenuMedicmtRgstrBinding

class MedicamentosRegistroMapaActivity : AppCompatActivity() {

    //private lateinit var drawerLayout: DrawerLayout

    private lateinit var bindingMenuRegistroMedicamento:ActivityMenuMedicmtRgstrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_menu)
        bindingMenuRegistroMedicamento=ActivityMenuMedicmtRgstrBinding.inflate(layoutInflater)
        setContentView(bindingMenuRegistroMedicamento.root)


        bindingMenuRegistroMedicamento.activityMedicamentoRegistro.imgMenu.setOnClickListener {
            bindingMenuRegistroMedicamento.drawerLayoutActivityMenu.openDrawer(GravityCompat.START)
        }

        bindingMenuRegistroMedicamento.navView.setNavigationItemSelectedListener { menuItem ->
            // Manejar los clics en los elementos del menú aquí
            true
        }

        bindingMenuRegistroMedicamento.activityMedicamentoRegistro.edtTextImputFechaInicio.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Muestra la fecha seleccionada en el EditText
                bindingMenuRegistroMedicamento.activityMedicamentoRegistro.txtInputLayoutFechaInicio.editText?.setText("" + dayOfMonth + "/" + (monthOfYear+1) + "/" + year)
            }, year, month, day)

            dpd.show()
        }

        /*bindingMenuRegistroMedicamento.activityMedicamentoRegistro.edtTextImputHoraInicio.setOnClickListener {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                // Muestra la hora seleccionada en el EditText
                bindingMenuRegistroMedicamento.activityMedicamentoRegistro.txtInputLayoutHoraInicio.editText?.setText(String.format("%02d:%02d", hourOfDay, minute))
            }, hour, minute, true)

            tpd.show()
        }*/

        bindingMenuRegistroMedicamento.activityMedicamentoRegistro.edtInputCaducidad.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Muestra la fecha seleccionada en el EditText
                bindingMenuRegistroMedicamento.activityMedicamentoRegistro.txtInputCaducidad.editText?.setText("" + dayOfMonth + "/" + (monthOfYear+1) + "/" + year)
            }, year, month, day)

            dpd.show()
        }


    }
}