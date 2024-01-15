package com.brayandev.listtaskapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ListTaskApp : Application() {

    /*override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ListTaskApp)
            modules(dataModule + domainModule + listOf(dataAppModule))
        }
    }*/
}
