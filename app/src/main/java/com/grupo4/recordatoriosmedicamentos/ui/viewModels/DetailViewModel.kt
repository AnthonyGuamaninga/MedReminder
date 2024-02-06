package com.grupo4.recordatoriosmedicamentos.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo4.recordatoriosmedicamentos.logic.entities.FullInfoDrugsLG
import com.grupo4.recordatoriosmedicamentos.logic.usercases.fda.FdaGetInfoDrugUserCase
import com.grupo4.recordatoriosmedicamentos.logic.usercases.fda.FdaGetResultDrugsUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel: ViewModel() {

    val drug = MutableLiveData<FullInfoDrugsLG>()
    val error = MutableLiveData<String>()

    fun loadInfoDrug(idDrug: String){
        viewModelScope.launch (Dispatchers.Main) {

            val data = withContext(Dispatchers.IO){
                FdaGetInfoDrugUserCase().invoke(idDrug)
            }
            data.onSuccess {
                //it.name = it.name +" UCE"
                drug.postValue(it)
            }
            data.onFailure {

            }

        }
    }
}