package com.example.testkotlin
class Model {
    private val list: MutableList<Int>

    constructor() {
        list = mutableListOf(0, 0)
    }

    fun addValueOfIndex(index: Int) : Int {
        return ++list[index]
    }

}