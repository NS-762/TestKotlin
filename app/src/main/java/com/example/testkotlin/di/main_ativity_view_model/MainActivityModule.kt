package com.example.testkotlin.di.main_ativity_view_model

import androidx.lifecycle.ViewModel
import com.example.testkotlin.Loader
import com.example.testkotlin.di.annotation.ViewModelKey
import com.example.testkotlin.view_model.MainActivityVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @IntoMap
    @ViewModelKey(MainActivityVM::class)
    @Binds
    abstract fun bindViewModel(viewModel: MainActivityVM): ViewModel
}