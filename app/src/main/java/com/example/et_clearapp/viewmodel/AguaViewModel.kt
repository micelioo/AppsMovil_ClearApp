package com.example.et_clearapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AguaViewModel : ViewModel() {

    var vasos: Int by mutableStateOf(0)
        private set

    fun sumarVaso() {
        vasos += 1
    }

    fun restarVaso() {
        if (vasos > 0) vasos -= 1
    }
}
