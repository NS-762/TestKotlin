package com.example.testkotlin

class Presenter {

    private lateinit var model: Model
    private lateinit var iView: IView

    constructor(iView: IView) {
        this.iView = iView
        model = Model()
    }

    fun buttonClick(idBtn: Int) {
        var newValue: Int
        when (idBtn) {
            R.id.btn1 -> {
                newValue = calcNewValue(model.getValueOfIndex(0))
                model.setValueOfIndex(0, newValue)
                iView.setButtonText(R.id.btn1, "Количество = " + newValue)
            }
            R.id.btn2 -> {
                newValue = calcNewValue(model.getValueOfIndex(1))
                model.setValueOfIndex(1, newValue)
                iView.setButtonText(R.id.btn2, "Количество = " + newValue)
            }
        }
    }

    private fun calcNewValue(value: Int) : Int {
        return value + 1
    }
}
