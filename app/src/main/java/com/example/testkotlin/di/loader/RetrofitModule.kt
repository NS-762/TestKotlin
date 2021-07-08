package com.example.testkotlin.di.loader

import com.example.testkotlin.Constants.Companion.BASE_URL
import com.example.testkotlin.IOpenRecipe
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class RetrofitModule() {

    @Named("base url")
    @Provides
    fun provideURL() : String{
        return "https://api.edamam.com"
    }


    @Provides
    fun provideApi(retrofit: Retrofit) : IOpenRecipe{
        return retrofit.create(IOpenRecipe::class.java)
    }

    @Provides
    fun provideRetrofit(@Named("base url")baseURL: String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}