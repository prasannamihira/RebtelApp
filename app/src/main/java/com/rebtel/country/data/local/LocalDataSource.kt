package com.rebtel.country.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.rebtel.country.app.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource {


    private var mSp: SharedPreferences

    @Inject
    constructor(context: Context) {
        mSp = PreferenceManager.getDefaultSharedPreferences(context)
    }

    /**
     * get preference
     */
    fun getSp(): SharedPreferences {
        return mSp
    }

    /**
     * save value with a key
     * @param value
     * @param key
     */
    fun saveString(key: String, value: String) {
        getSp().edit().putString(key, value).apply()
    }

    fun logOut() {
        mSp.logout()
    }
}