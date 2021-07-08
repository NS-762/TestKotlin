package com.example.testkotlin.di.main_ativity_view_model

import com.example.testkotlin.activity.MainActivity
import com.example.testkotlin.di.loader.MapModule
import com.example.testkotlin.di.loader.RecipeDatabaseModule
import com.example.testkotlin.di.loader.RetrofitModule
import com.example.testkotlin.view_model.MainActivityVM
import dagger.Subcomponent

@Subcomponent(modules = [LoaderModule::class, MainActivityModule::class, RetrofitModule::class,
    MapModule::class, RecipeDatabaseModule::class])
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }
}