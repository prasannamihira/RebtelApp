package com.rebtel.country.data.local

import android.content.SharedPreferences

class SharedPref {
}

fun SharedPreferences.save(key: String, value: String?) = edit().putString(key, value).apply()

fun SharedPreferences.logout() = edit().clear().apply()