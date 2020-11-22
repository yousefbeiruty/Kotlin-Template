package com.it.yousefl.kotlintemplate.di

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.it.yousefl.kotlintemplate.R
import com.it.yousefl.kotlintemplate.network.MainApi
import com.it.yousefl.kotlintemplate.remote.OrdersRemoteDataSource
import com.it.yousefl.kotlintemplate.repository.MainRepository
import com.it.yousefl.kotlintemplate.room.AppDatabase
import com.it.yousefl.kotlintemplate.room.OrdersDao
import com.it.yousefl.kotlintemplate.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {



    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(gson: Gson):Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }
    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder):MainApi{
        return retrofit.build().create(MainApi::class.java)
    }
    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions? {
        return RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(
        application: Application?,
        requestOptions: RequestOptions?
    ): RequestManager? {
        return Glide.with(application!!)
            .setDefaultRequestOptions(requestOptions!!)
    }

    @Singleton
    @Provides
    fun provideAppDrawable(application: Application?): Drawable? {
        return ContextCompat.getDrawable(application!!, R.drawable.ic_launcher_background)
    }

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: MainApi) = OrdersRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.ordersDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: OrdersRemoteDataSource,
                          localDataSource: OrdersDao) =
        MainRepository(remoteDataSource, localDataSource)
}