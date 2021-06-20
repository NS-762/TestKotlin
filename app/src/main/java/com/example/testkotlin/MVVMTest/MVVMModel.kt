package com.example.testkotlin.MVVMTest

class MVVMModel {

    private val list: MutableList<Int>

    constructor() {
        list = mutableListOf(0, 0)
    }

    fun addValueOfIndex(index: Int) : Int {
        return ++list[index]
    }
}