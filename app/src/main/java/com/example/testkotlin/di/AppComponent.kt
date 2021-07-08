package com.example.testkotlin.di

import com.example.testkotlin.di.loader.LoaderComponent
import com.example.testkotlin.di.main_ativity_view_model.MainActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
//    fun plus(
//        mapNodule: MapModule, recipeDatabaseModule: RecipeDatabaseModule,
//        retrofitModule: RetrofitModule
//    ): LoaderComponent

//    fun plus(loaderModule: LoaderModule): MainActivityVMComponent


    fun getLoaderComponentFactory(): LoaderComponent.Factory
    fun getMainActivityComponentFactory(): MainActivityComponent.Factory
}