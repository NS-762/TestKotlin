package com.example.testkotlin

import moxy.MvpPresenter


class Presenter : MvpPresenter<IView>() {

    private lateinit var model: Model

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        model = Model()
    }

    override fun attachView(view: IView?) {
        super.attachView(view)
    }

    fun buttonOneClick() {
        viewState.setButtonOneText("Количество = " + model.addValueOfIndex(0))
    }

    fun buttonTwoClick() {
        viewState.setButtonOneText("Количество = " + model.addValueOfIndex(1))
    }
}
