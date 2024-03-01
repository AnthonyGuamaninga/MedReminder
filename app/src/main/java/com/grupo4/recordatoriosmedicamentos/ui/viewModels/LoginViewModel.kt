package com.grupo4.recordatoriosmedicamentos.ui.viewModels

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.UserDB
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.user.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val user get() = _user
    private val _user = MutableLiveData<UserDB>()
    val error get() = _error
    private val _error = MutableLiveData<String>()

    val resultCheckBiometric = MutableLiveData<Int>()
    fun checkBiometric(context: Context) {
        val biometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                resultCheckBiometric.postValue(BiometricManager.BIOMETRIC_SUCCESS)
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                resultCheckBiometric.postValue(BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE)
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                resultCheckBiometric.postValue(BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE)
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                resultCheckBiometric.postValue(BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED)
                // Prompts the user to create credentials that your app accepts.
            }
        }
    }

    fun singInUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val us = UserUseCase().singIn(email, password)
            if (us != null) {
                _user.postValue(us!!)
            } else {
                _error.postValue("Ocurrio un error")
            }
        }

    }
}