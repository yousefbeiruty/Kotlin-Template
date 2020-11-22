package com.it.yousefl.kotlintemplate.di

import com.it.yousefl.kotlintemplate.network.MainApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object MainModule {

//    @Singleton
//    @Provides
//     fun provideMainApi(retrofit: Retrofit): MainApi? {
//        return retrofit.create(MainApi::class.java)
//    }
}