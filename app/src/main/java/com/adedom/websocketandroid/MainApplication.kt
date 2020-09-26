package com.adedom.websocketandroid

import android.app.Application
import com.adedom.websocketandroid.data.datasource.di.getDataSource
import com.adedom.websocketandroid.data.repository.di.getRepositoryModule
import com.adedom.websocketandroid.presentation.di.getPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            koin.loadModules(listOf(getPresentationModule, getRepositoryModule, getDataSource))
            koin.createRootScope()
        }
    }
}
