package com.example.testkotlin.MVVMTest

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MyViewModel : ViewModel() {

    private var model: MVVMModel
    private var subject: PublishSubject<Long>
    private var observableInterval: Observable<Long>
    var firstLiveData = MutableLiveData<String>()
    var secondLiveData = MutableLiveData<String>()
    var thirdLiveData = MutableLiveData<String>()

    init {
        model = MVVMModel()
        observableInterval = Observable.interval(1, TimeUnit.SECONDS)
        subject = PublishSubject.create()
        observableInterval.subscribe(subject)
    }

    fun getSubject() : PublishSubject<Long> {
        return subject
    }

    fun buttonOneClick() {
        firstLiveData.value = "Счетчик 1 = ${model.addValueOfIndex(0)}"
    }

    fun buttonTwoClick() {
        secondLiveData.value = "Счетчик 2 = ${model.addValueOfIndex(1)}"
    }

    fun buttonThreeClick() {
        var observable: Observable<String> = Observable.fromIterable(model.getListOfStrings())
        observable.subscribe(
            {
                thirdLiveData.value = "$it"
            })
    }

    fun buttonAddValueClick() {
        subject.onNext(999)
    }
}




