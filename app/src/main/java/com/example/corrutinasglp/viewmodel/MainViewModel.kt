package com.example.corrutinasglp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var resultState by mutableStateOf("")
        private set

    var counTimeA by mutableStateOf(0)
        private set

    var counTimeB by mutableStateOf(0)
        private set

    var oneCount by mutableStateOf(false)
        private set

    var job: Job? = null

    fun fetchData(){
        job = viewModelScope.launch{
            for (i in 1..5) {
                delay(1000)
                counTimeA = i
            }

            for (i in 1..5){
                delay(1000)
                counTimeB = i
            }

            oneCount = true
        }

        if (oneCount){
            cancelJob()
        }

        viewModelScope.launch{
            delay(10000)
            resultState = "Respuesta de la Web o API"
        }
    }

    fun cancelJob(){
        job?.cancel()
    }
}