package com.rebtel.country.util

interface Config {

    companion object {
        val TIME_OUT = 300
        val TIME_OUT_RW = 300

        const val CONTENT_TYPE_JSON = "Content-Type:application/json"
        const val ACCESS_TOKEN_PREFIX = "x-access-token"

        const val EXTRA_LIST_ROW = "list_row"

        val DEVICE_TYPE = "android"
        const val AUTHORIZATION = "Authorization"
    }

}