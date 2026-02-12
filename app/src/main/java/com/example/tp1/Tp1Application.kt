package com.example.tp1

import android.app.Application
import com.example.tp1.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Tp1Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Tp1Application)
            modules(appModule)
        }
    }
}

