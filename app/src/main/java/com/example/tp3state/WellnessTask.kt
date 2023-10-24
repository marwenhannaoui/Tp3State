package com.example.tp3state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class WellnessTask(val id: Int, val label: String,    var checkedState: MutableState<Boolean> =  mutableStateOf(false)) {


}

