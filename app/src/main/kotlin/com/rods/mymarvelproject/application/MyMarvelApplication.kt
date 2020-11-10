package com.rods.mymarvelproject.application

import android.app.Application
import com.rods.core.di.buildCoreNetworkModule
import com.rods.injection.character.charactersListFeatureModule
import com.rods.mymarvelproject.BuildConfig
import com.rods.mymarvelproject.di.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyMarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyMarvelApplication)
            modules(charactersListFeatureModule)
            modules(
                buildCoreNetworkModule(
                    BuildConfig.API_URL,
                    BuildConfig.PUBLIC_KEY,
                    BuildConfig.PRIVATE_KEY
                ), navigationModule
            )
        }
    }
}