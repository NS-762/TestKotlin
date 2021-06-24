package com.example.testkotlin

import android.os.Bundle
import android.widget.Button
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), IView {

    private lateinit var btn1: Button
    private lateinit var btn2: Button

    @InjectPresenter
    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener {
            presenter.buttonOneClick()
        }

        btn2 = findViewById(R.id.btn2)
        btn2.setOnClickListener {
            presenter.buttonTwoClick()
        }
    }

    override fun setButtonOneText(str: String) {
        btn1.setText(str)
    }

    override fun setButtonTwoText(str: String) {
        btn2.setText(str)
    }

    fun createStringObserver(): Observer<String> {
        val observer = object: Observer<String> {
            override fun onNext(s: String) {
                println(s)
            }

            override fun onComplete() {
                println("onComplete")
            }

            override fun onError(e: Throwable) {
                println("onError")
            }

            override fun onSubscribe(d: Disposable?) {
                println("onSubscribe")
            }
        }

        return observer
    }
}
