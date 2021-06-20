package com.example.testkotlin.MVVMTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testkotlin.R

class MainActivity2 : AppCompatActivity() {

    private lateinit var btnMVVM1: Button
    private lateinit var btnMVVM2: Button
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        btnMVVM1 = findViewById(R.id.btn_MVVM_1)
        btnMVVM1.setOnClickListener{
            myViewModel.buttonOneClick()
        }

        btnMVVM2 = findViewById(R.id.btn_MVVM_2)
        btnMVVM2.setOnClickListener{
            myViewModel.buttonTwoClick(it.id)
        }

        myViewModel.apply {
            firstCounter.observeForever { setBtnMVVMOneText(it) }
            secondCounter.observeForever { setBtnMVVMTwoText(it) }
        }
    }

    fun setBtnMVVMOneText(str: String) {
        btnMVVM1.setText(str)
    }

    fun setBtnMVVMTwoText(str: String) {
        btnMVVM2.setText(str)
    }
}