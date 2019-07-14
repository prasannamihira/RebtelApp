package com.rebtel.country.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import org.apache.commons.lang3.text.WordUtils


fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

/**
 * check the device internet connectivity
 */
fun Context.isInternetOn() : Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return (activeNetworkInfo != null && activeNetworkInfo.isConnected)
}

/**
 * return the first capital letter from given word
 */
fun String.toCapitalizeFirstLetterOfEach(): String = WordUtils.capitalize(this)