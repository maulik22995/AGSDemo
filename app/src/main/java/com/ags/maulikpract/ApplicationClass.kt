package com.ags.maulikpract

import android.app.Application
import com.ags.maulikpract.di.appModule
import com.ags.maulikpract.di.stateModule
import com.ags.maulikpract.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(listOf(appModule, stateModule, viewModelModule))
        }
    }
}