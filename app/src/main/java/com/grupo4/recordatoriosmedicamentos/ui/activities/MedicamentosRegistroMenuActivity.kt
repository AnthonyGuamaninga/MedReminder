package com.grupo4.recordatoriosmedicamentos.ui.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import com.grupo4.recordatoriosmedicamentos.databinding.ActivityMenuLateralBinding
import com.grupo4.recordatoriosmedicamentos.databinding.ActivityMenuMedicmtRgstrBinding

class MedicamentosRegistroMenuActivity : AppCompatActivity() {

    //private lateinit var drawerLayout: DrawerLayout

    private lateinit var bindingMenuLateral:ActivityMenuLateralBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_menu)
        bindingMenuLateral=ActivityMenuLateralBinding.inflate(layoutInflater)
        setContentView(bindingMenuLateral.root)


        bindingMenuLateral.activityMenuLateral.imgMenu.setOnClickListener {
            bindingMenuLateral.drawerLayoutActivityMenu.openDrawer(GravityCompat.START)
        }

        bindingMenuLateral.navView.setNavigationItemSelectedListener { menuItem ->
            // Manejar los clics en los elementos del menú aquí
            true
        }







    }
}