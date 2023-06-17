package com.savan.myroomkoin

import android.app.Application
import com.savan.myroomkoin.di.injectFeature
import com.savan.myroomkoin.di.injectNetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class EmpApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    private fun configureDi() {
        startKoin {
            androidLogger()
            androidContext(this@EmpApplication)
            injectFeature()
            injectNetworkModule()
        }
    }


    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}