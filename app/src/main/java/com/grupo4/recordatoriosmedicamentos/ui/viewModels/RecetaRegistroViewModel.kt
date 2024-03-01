package com.grupo4.recordatoriosmedicamentos.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.MedInfo
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.UserDB
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta.MedicamentosUseCase
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta.RecetaUseCase
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.user.UserUseCase
import kotlinx.coroutines.launch

class RecetaRegistroViewModel : ViewModel() {

    val receta get() = _recetaInfo
    private val _recetaInfo = MutableLiveData<Receta>()
    val user get() = _user
    private val _user = MutableLiveData<UserDB>()


    fun nuevaReceta(
        id: String,
        estado: Boolean,
        medicamentos: List<String>?,
        idUser: String,
        fecha: String
    ) {
        viewModelScope.launch {
            val rec = RecetaUseCase().Save(id, estado, medicamentos!!, idUser, fecha)
            _recetaInfo.postValue(rec!!)
        }
    }

    fun updateUser(userDB: UserDB) {
        viewModelScope.launch {
            UserUseCase().update(userDB)
        }
    }

    suspend fun getUser(idUser: String): UserDB? {
        return UserUseCase().getById(idUser)
    }

    fun obtenerListIdMed(lista: MutableList<MedInfo>): List<String>? {
        var res = ArrayList<String>()
        for (obj in lista) {
            res.add(obj.id)
        }
        return res.toList()
    }

    suspend fun guardarMed(list: MutableList<MedInfo>) {

            for (medi in list) {
                MedicamentosUseCase().Save(
                    medi.id,
                    medi.dosis!!,
                    medi.frecuencia!!,
                    medi.f_inicio!!,
                    medi.hora_inicio!!,
                    medi.caducidad!!,
                    medi.indicaciones!!,
                    medi.observaciones!!,
                    medi.medId!!
                )
            }
    }

}

