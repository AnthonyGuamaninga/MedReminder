package com.grupo4.recordatoriosmedicamentos.ui.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.UserDB
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta.RecetaUseCase
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.user.GetUserByIdUserCase
import kotlinx.coroutines.launch

class RecetaRegistroViewModel : ViewModel()  {

    val receta get() = _recetaInfo
    private val _recetaInfo = MutableLiveData<Receta>()
    val user get() = _user
    private val _user = MutableLiveData<UserDB>()

    fun nuevaReceta(
        id:String,
        estado:Boolean,
        medicamentos: MutableList<String>?,
        idUser:String,
        fecha:String){
        viewModelScope.launch {
            val rec = RecetaUseCase().Save(id, estado, medicamentos!!, idUser, fecha)
            _recetaInfo.postValue(rec!!)
        }
    }

    fun getUser(idUser: String):UserDB?{
        var user:UserDB? = null
        viewModelScope.launch {
            var temp= GetUserByIdUserCase().invoke(idUser)!!
            Log.d("UCE34",temp.toString())
            _user.postValue(temp!!)
            user = temp
        }
        return user
    }
}