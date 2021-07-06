package com.example.testkotlin.di.main_ativity_view_model

import com.example.testkotlin.di.loader.LoaderComponent
import com.example.testkotlin.view_model.MainActivityVM
import dagger.Component
import dagger.Subcomponent

@Subcomponent(modules = [LoaderModule::class])
interface MainActivityVMComponent {

    fun inject(mainActivityVM: MainActivityVM)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityVMComponent
    }
}