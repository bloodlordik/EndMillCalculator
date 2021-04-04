package com.example.endmillcalculator.fragments

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endmillcalculator.utils.eccentricContactCalc

class EccenticViewModel : ViewModel() {
    private var inputHelixState = ""
    private var inputLandState = ""
    private val result = MutableLiveData<Double>(0.0)
    val onResult:LiveData<Double> get() = result
    val inputText get() = inputHelixState
    val inputLand get() = inputLandState
    fun setInputHelix(text: String){
        inputHelixState = text
    }
    fun  setInputLand(text:String){
        inputLandState = text
    }

    fun calculate():Unit{
        val helix = inputHelixState.ifBlank { "0" }.toDouble()
        val land = inputLandState.ifBlank { "0" }.toDouble()

        result.value = eccentricContactCalc(landWidth = land, helixAngel = helix)
    }
}