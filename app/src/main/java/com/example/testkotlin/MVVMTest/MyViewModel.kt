package com.example.testkotlin.MVVMTest

import io.reactivex.rxjava3.core.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testkotlin.Constants.Companion.APP_ID
import com.example.testkotlin.Constants.Companion.APP_KEY
import com.example.testkotlin.RecipeRequest
import com.example.testkotlin.RetrofitInstance
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.concurrent.TimeUnit


class MyViewModel : ViewModel() {

    private var model: MVVMModel
    private var subject: PublishSubject<Long>
    private var observableInterval: Observable<Long>
    var firstLiveData = MutableLiveData<String>()
    var secondLiveData = MutableLiveData<String>()
    var thirdLiveData = MutableLiveData<String>()
    var disposable: Disposable? = null

    init {
        model = MVVMModel()
        observableInterval = Observable.interval(1, TimeUnit.SECONDS)
        subject = PublishSubject.create()
        observableInterval.subscribe(subject)


    }

    fun loadRecipes(product: String) {
        val response = RetrofitInstance.api.loadRecipes("chicken", APP_ID, APP_KEY

        )
/*            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    result ->
                Log.d("Result", "There are ${result.items.size} Java developers in Lagos")
            }*/
        response.enqueue(object : Callback<RecipeRequest> {

            override fun onFailure(call: Call<RecipeRequest>, t: Throwable) {
                val ddd = "123"
            }

            override fun onResponse(
                call: Call<RecipeRequest>,
                response: Response<RecipeRequest>
            ) {
                val str = "3"
                val request = response.body()
            }
        })


    }


    fun getSubject(): PublishSubject<Long> {
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
        disposable = observable.delay(1000, TimeUnit.MILLISECONDS).subscribe(
            {
                thirdLiveData.value = "$it"
            })
    }

    fun a1aa2ss() {
        disposable?.dispose()
    }

    fun buttonAddValueClick() {
        subject.onNext(999)
    }
}




