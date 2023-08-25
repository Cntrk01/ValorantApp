package com.movieapp.valorantapp.ui.Bundle.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.valorantapp.agents_buddies_dataclass.Agents
import com.movieapp.valorantapp.agents_buddies_dataclass.BaseModel
import com.movieapp.valorantapp.bundle.Bundle
import com.movieapp.valorantapp.service.ValorantAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BundleViewModel @Inject constructor(private val api: ValorantAPI) : ViewModel() {

    private val _bundle = MutableLiveData<BaseModel<Array<Bundle>>>()
    val bundlesData: LiveData<BaseModel<Array<Bundle>>> get() = _bundle
    var progresBar: MutableLiveData<Boolean> = MutableLiveData(true)
    var errorMessage: MutableLiveData<Boolean> = MutableLiveData()


    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        progresBar.value=true
        errorMessage.value=false
        val response=api.bundles()
        if (response.isSuccessful){
            val bundlle=response.body()!!
            _bundle.value=bundlle
            progresBar.value=false
            errorMessage.value=false
        }else{
            errorMessage.value = true
            progresBar.value=false
        }

    }
}