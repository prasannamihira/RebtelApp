package com.rebtel.country.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource {

    val AUTH_TOKEN = "authToken"

    private var mSp: SharedPreferences

    @Inject
    constructor(context: Context) {
        mSp = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getSp(): SharedPreferences {
        return mSp
    }


}