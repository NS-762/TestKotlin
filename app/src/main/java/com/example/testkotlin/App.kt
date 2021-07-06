package com.example.testkotlin

import android.app.Application
import com.example.testkotlin.di.AppComponent
import com.example.testkotlin.di.AppModule
import com.example.testkotlin.di.DaggerAppComponent
import com.example.testkotlin.di.loader.LoaderComponent
import com.example.testkotlin.di.main_ativity_view_model.MainActivityVMComponent

class App : Application() {

    companion object {
         var instance: App? = null
            private set
    }

    private lateinit var appComponent: AppComponent
    private var mainActivityVMComponent: MainActivityVMComponent? = null
    private var loaderComponent: LoaderComponent? = null

    override fun onCreate() {
        super.onCreate()

        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }

    fun createLoaderComponent(): LoaderComponent? {
        loaderComponent = appComponent.getLoaderComponentFactory().create()
        return loaderComponent
    }

    fun createMainActivityVMComponent(): MainActivityVMComponent? {
        mainActivityVMComponent = appComponent.getMainActivityVMComponentFactory().create()
        return mainActivityVMComponent
    }

}