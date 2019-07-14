package com.rebtel.country.di

import com.rebtel.country.app.App
import com.rebtel.country.ui.country.list.CountryListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: App)
    fun inject(activity: CountryListActivity)
}