package com.example.testkotlin

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface IView : MvpView {
    fun setButtonOneText(str: String)
    fun setButtonTwoText(str: String)
}