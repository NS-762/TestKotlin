package com.example.testkotlin.MVVMTest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testkotlin.R

class MyViewModel : ViewModel() {

    private var model: MVVMModel

    var firstCounter = MutableLiveData<String>()
    var secondCounter = MutableLiveData<String>()
    var thirdCounter = MutableLiveData<String>()

    init {
        model = MVVMModel()
    }

    fun buttonOneClick() {
        firstCounter.value = "Счетчик 1 = ${model.addValueOfIndex(0)}"
    }

    fun buttonTwoClick() {
        secondCounter.value = "Счетчик 2 = ${model.addValueOfIndex(1)}"
    }
}