package com.brayandev.listtaskapp

import android.app.Application
import com.brayandev.listtaskapp.di.dataModule
import com.brayandev.listtaskapp.di.domainModule
import com.brayandev.listtaskapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ListTaskApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ListTaskApp)
            modules(viewModelModule, domainModule, dataModule)
        }
    }
}
