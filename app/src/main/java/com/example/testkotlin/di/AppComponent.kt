package com.example.testkotlin.di

import com.example.testkotlin.Loader
import com.example.testkotlin.viewModel.MainActivityViewModel
import dagger.Component

@Component(modules = [LoaderModule::class])
interface AppComponent {

    fun injectToMainActivityViewModel(mainActivityViewModel: MainActivityViewModel)
    fun getLoader(): Loader

}