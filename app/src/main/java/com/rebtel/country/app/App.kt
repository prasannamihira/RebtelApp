package com.rebtel.country.app

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.rebtel.country.di.AppComponent
import com.rebtel.country.di.AppModule
import com.rebtel.country.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }
}