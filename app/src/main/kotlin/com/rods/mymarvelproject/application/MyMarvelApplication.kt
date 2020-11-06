package com.rods.mymarvelproject.application

import android.app.Application
import com.rods.core.di.buildCoreNetworkModule
import com.rods.mymarvelproject.BuildConfig
import org.koin.core.context.startKoin

class MyMarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(buildCoreNetworkModule(BuildConfig.API_URL, "", ""))
        }
    }
}