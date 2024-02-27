package com.grupo4.recordatoriosmedicamentos.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta.SaveRecetaDBUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicamentoRegistroViewModel : ViewModel() {
    val receta get() = _receta
    private val _receta = MutableLiveData<Receta>()
    fun createReceta(
        dosis: String,
        indicaciones: String,
        frecuencia: String,
        f_inicio: String,
        caducidad: String,
        observaciones: String,
        medId: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val rec = SaveRecetaDBUserCase().invoke(
                dosis,
                indicaciones,
                frecuencia,
                f_inicio,
                caducidad,
                observaciones,
                medId
            )
            _receta.postValue(rec!!)
        }
    }
}