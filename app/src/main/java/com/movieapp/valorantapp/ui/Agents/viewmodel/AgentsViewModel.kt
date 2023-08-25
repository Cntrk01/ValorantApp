package com.movieapp.valorantapp.ui.Agents.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.valorantapp.agents_buddies_dataclass.Agents
import com.movieapp.valorantapp.agents_buddies_dataclass.BaseModel
import com.movieapp.valorantapp.ui.Agents.repository.AgentsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(private val agentsRepo: AgentsRepo) : ViewModel(){

    private val _agents = MutableLiveData<BaseModel<Array<Agents>>>()
    val agentsData: LiveData<BaseModel<Array<Agents>>> get() = _agents
    var progresBar: MutableLiveData<Boolean> = MutableLiveData(true)
    var errorMessage: MutableLiveData<Boolean> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        progresBar.value=true
        errorMessage.value=false
            val response=agentsRepo.getAgentsApi()
            if (response.isSuccessful){
                val agents=response.body()!!
                _agents.value=agents
                progresBar.value=false
                errorMessage.value=false
            }else{
                errorMessage.value = true
                progresBar.value=false
            }

    }
}