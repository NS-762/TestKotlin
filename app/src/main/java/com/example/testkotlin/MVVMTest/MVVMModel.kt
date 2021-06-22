package com.example.testkotlin.MVVMTest

import java.util.ArrayList

class MVVMModel {

    private val list: MutableList<Int>
    private val listOfString: List<String>

    constructor() {
        list = mutableListOf(0, 0)
        listOfString = listOf("Hello", ",", "World", "!")
    }

    fun getListOfStrings() : List<String> {
        return listOfString
    }

    fun addValueOfIndex(index: Int) : Int {
        return ++list[index]
    }
}