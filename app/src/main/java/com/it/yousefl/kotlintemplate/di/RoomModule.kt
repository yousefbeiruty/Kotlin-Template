package com.it.yousefl.kotlintemplate.di

import android.content.Context
import androidx.room.Room
import com.it.yousefl.kotlintemplate.room.OrderDatabse
import com.it.yousefl.kotlintemplate.room.OrdersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogDB(@ApplicationContext context: Context):OrderDatabse{
        return Room.databaseBuilder(context,OrderDatabse::class.java,OrderDatabse.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDlogADO(blogDatabse: OrderDatabse):OrdersDao{
        return blogDatabse.ordergDao()
    }
}