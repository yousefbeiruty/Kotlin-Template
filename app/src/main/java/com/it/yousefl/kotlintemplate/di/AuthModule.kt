package com.it.yousefl.kotlintemplate.di

import com.it.yousefl.kotlintemplate.network.AuthApi
import com.it.yousefl.kotlintemplate.repository.AuthRepository
import com.it.yousefl.kotlintemplate.utils.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AuthModule {

    @Singleton
    @Provides
     fun provideAuthApi(retrofit: Retrofit.Builder): AuthApi {
        return retrofit.build().create(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMainRepository(retrofit: AuthApi
                              ,networkMapper: NetworkMapper):AuthRepository{
        return AuthRepository(retrofit,networkMapper)
    }

}