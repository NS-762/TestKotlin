package com.example.testkotlin

import android.app.Application
import com.example.testkotlin.di.AppComponent
import com.example.testkotlin.di.DaggerAppComponent

class App: Application() {

    // TODO: 28.06.2021 Сделать поле приватным и добавить геттер
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}