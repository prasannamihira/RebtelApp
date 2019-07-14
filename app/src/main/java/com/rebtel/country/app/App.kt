package com.rebtel.country.app

import android.app.Application
import com.rebtel.country.di.AppComponent
import com.rebtel.country.di.AppModule
import com.rebtel.country.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        } else {
            LeakCanary.install(this)
        }

        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }
}