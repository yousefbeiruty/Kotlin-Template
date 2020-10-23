package com.it.yousefl.kotlintemplate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        //BaseApplication
        @get:Synchronized
        var instance: BaseApplication? = null
            private set
    }
}