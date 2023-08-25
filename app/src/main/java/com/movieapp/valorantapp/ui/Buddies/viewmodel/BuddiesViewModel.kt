package com.movieapp.valorantapp.ui.Buddies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.valorantapp.agents_buddies_dataclass.BaseModel
import com.movieapp.valorantapp.agents_buddies_dataclass.Buddies
import com.movieapp.valorantapp.ui.Buddies.repository.BuddiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuddiesViewModel @Inject constructor(private val repository: BuddiesRepository) :
    ViewModel() {
    private val buddies = MutableLiveData<BaseModel<Array<Buddies>>>()
    val buddiesData: LiveData<BaseModel<Array<Buddies>>> get() = buddies
    var progresBar: MutableLiveData<Boolean> = MutableLiveData(true)
    var errorMessage: MutableLiveData<Boolean> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        progresBar.value = true
        errorMessage.value = false
        val response = repository.getBuddies()
            if (response.isSuccessful) {
                val buddies1 = response.body()!!
                buddies.value = buddies1
                progresBar.value = false
                errorMessage.value = false
            } else {
                errorMessage.value = true
                progresBar.value = false
        }
    }
}