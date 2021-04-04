package com.example.endmillcalculator.fragments

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endmillcalculator.models.BackTaperModel
import com.example.endmillcalculator.utils.backTaperCalculationAngel
import com.example.endmillcalculator.utils.backTaperCalculationDiameter
import com.example.endmillcalculator.utils.limitAfterPoint

class BackTaperViewModel : ViewModel() {
    private var state = BackTaperModel()
    private val resultOd = MutableLiveData<Double>(0.0)
    private val resultAngle = MutableLiveData<Double>(0.0)
    val resultDiameter:LiveData<Double> get() = resultOd
    val resultBackTaperAngel:LiveData<Double> get() = resultAngle
    fun setState(tool:String, lowOd:String, len:String):Unit{
        state = BackTaperModel(toolOd = tool, lowering = lowOd, length = len)
    }
    fun getState(call:(BackTaperModel)->Unit):Unit{
        call(state)
    }
    fun calculate(toolOd:Double, lowering:Double, length:Double):Unit{
        val sum1 = toolOd + lowering + length
        val sum2 = toolOd + length
        resultOd.value = backTaperCalculationDiameter(toolOd = toolOd, lowering = lowering, length = length).limitAfterPoint(x = 4)
        resultAngle.value = backTaperCalculationAngel(toolOd = toolOd, lowering = lowering, length = length).limitAfterPoint(x = 4)
    }

}