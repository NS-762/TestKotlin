package com.example.testkotlin.MVVMTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.testkotlin.R

class MainActivity2 : AppCompatActivity() {

    private lateinit var btnMVVM1: Button
    private lateinit var btnMVVM2: Button
    private lateinit var btnMVVM3: Button
    private lateinit var btnAddValue: Button
    private lateinit var textView: TextView

    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        textView = findViewById(R.id.textView)

        btnMVVM1 = findViewById(R.id.btn_MVVM_1)
        btnMVVM1.setOnClickListener{
            myViewModel.buttonOneClick()
        }

        myViewModel.loadRecipes("chicken")

        myViewModel.apply {
            firstLiveData.observeForever { setBtnMVVMOneText(it) }
            secondLiveData.observeForever { setBtnMVVMTwoText(it) }
            thirdLiveData.observeForever { setTextInTextView(it) }
        }


        myViewModel.getSubject().subscribe({
            Log.e("ObservableInterval", "$it")
        }, {

        }, {
            Log.e("ObservableInterval", "Complete")
        })

    }

    fun setBtnMVVMOneText(str: String) {
        btnMVVM1.text = str
    }

    fun setBtnMVVMTwoText(str: String) {
        btnMVVM2.text = str
    }

    fun setTextInTextView(str: String) {
        val newText =  (textView.text).toString() + str
        textView.text = newText
    }

}