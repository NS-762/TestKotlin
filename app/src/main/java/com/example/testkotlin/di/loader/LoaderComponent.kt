package com.example.testkotlin.di.loader

import android.content.Context
import com.example.testkotlin.Loader
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import javax.inject.Named

@Subcomponent(modules = [RetrofitModule::class, MapModule::class, RecipeDatabaseModule::class])
interface LoaderComponent {

    fun inject(loader: Loader)

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoaderComponent
    }
}