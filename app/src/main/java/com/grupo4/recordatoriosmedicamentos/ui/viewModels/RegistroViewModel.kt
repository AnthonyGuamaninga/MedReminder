package com.grupo4.recordatoriosmedicamentos.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.UserDB
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.user.SaveUserInDBUserCase
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.user.createUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistroViewModel : ViewModel() {
    val user get() = _user
    private val _user = MutableLiveData<UserDB>()
    val error get() = _error
    private val _error = MutableLiveData<String>()

    fun createUser(email: String, password: String, name: String, lastname:String, edad:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val us = createUserCase().invoke(email, password,name)
            if (us != null) {
                val newUs= SaveUserInDBUserCase().invoke(us.id,us.email,name,lastname,edad)
                _user.postValue(newUs!!)
            } else {
                _error.postValue("Ocurrio un error")
            }
        }

    }
}