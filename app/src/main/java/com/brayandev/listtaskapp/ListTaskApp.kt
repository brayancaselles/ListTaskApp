package com.brayandev.listtaskapp

import android.app.Application
import com.brayandev.listtaskapp.di.dataAppModule
import com.brayandev.listtaskapp.di.dataBaseModule
import com.brayandev.listtaskapp.di.repositoryModule
import com.brayandev.listtaskapp.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ListTaskApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ListTaskApp)
            modules(dataBaseModule,repositoryModule, useCaseModule, dataAppModule)
        }
    }
}
