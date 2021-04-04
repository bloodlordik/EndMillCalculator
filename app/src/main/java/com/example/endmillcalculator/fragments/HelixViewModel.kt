package com.example.endmillcalculator.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endmillcalculator.models.HelixModel
import com.example.endmillcalculator.utils.HelixConvertToLead
import com.example.endmillcalculator.utils.LeadConvertToHelix
import com.example.endmillcalculator.utils.limitAfterPoint

class HelixViewModel : ViewModel() {
    private var state = HelixModel()
    private val resultState = MutableLiveData<Double>(0.0)
    val result:LiveData<Double> get() = resultState
    fun setState(newState:HelixModel):Unit{
        state = newState
    }
    fun bindState(call:(HelixModel)->Unit):Unit{
        call(state)
    }
    fun calculate(model: HelixModel):Unit{
        val diameter = model.toolOd.ifBlank { "0" }.toDouble()
        val helixOrLead = model.helixOrLead.ifBlank { "0" }.toDouble()
        if(model.isChecked){
            resultState.value = LeadConvertToHelix(toolDiameter = diameter, lead = helixOrLead).limitAfterPoint(4)
        }else{
            resultState.value = HelixConvertToLead(toolDiameter = diameter, helixAngel = helixOrLead).limitAfterPoint(4)
        }
    }
}