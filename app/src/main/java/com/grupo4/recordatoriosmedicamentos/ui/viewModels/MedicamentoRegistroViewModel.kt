package com.grupo4.recordatoriosmedicamentos.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.MedInfo
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta.MedicamentosUseCase
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta.SaveRecetaDBUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicamentoRegistroViewModel : ViewModel() {
    val receta get() = _medInfo
    private val _medInfo = MutableLiveData<MedInfo>()
    fun createReceta(
        idReceta: String,
        dosis: String,
        frecuencia: String,
        f_inicio: String,
        hora_inicio:String,
        caducidad: String,
        indicaciones: String,
        observaciones: String,
        medId: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val rec = MedicamentosUseCase().Save(
                idReceta,
                dosis,
                frecuencia,
                f_inicio,
                hora_inicio,
                caducidad,
                indicaciones,
                observaciones,
                medId
            )
            _medInfo.postValue(rec!!)
        }
    }
}