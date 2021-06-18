package com.example.testkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity(), IView{

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter(this)

        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener{
            presenter.buttonClick(it.id)
        }

        btn2 = findViewById(R.id.btn2)
        btn2.setOnClickListener{
            presenter.buttonClick(it.id)
        }
    }

    override fun setButtonText(idBtn: Int, str: String) {
        when (idBtn) {
            R.id.btn1 -> {
                btn1.setText(str)
            }
            R.id.btn2 -> {
                btn2.setText(str)
            }
        }
    }
}