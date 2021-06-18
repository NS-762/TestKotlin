package com.example.testkotlin

class Model {
    private val list: MutableList<Int>

    constructor() {
        list = mutableListOf(0, 0)
    }

    fun getValueOfIndex(index: Int) : Int {
        return list.get(index)
    }

    fun setValueOfIndex(index: Int, newValue: Int){
        list.set(index, newValue)
    }

}