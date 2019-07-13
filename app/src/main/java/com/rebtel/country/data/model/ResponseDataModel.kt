package com.rebtel.country.data.model

/**
 * Country item data model
 * api response
 */
data class CountryResponseDataModel(
    val name: String?,
    var alpha2Code: String?,
    val alpha3Code: String?,
    val capital: String?,
    val region: String?,
    val subregion: String?,
    val population: Int?,
    val nativeName: String?,
    val currencies: Array<Currency>,
    val timezones: Array<String>,
    val flag: String?
)

/**
 * country currency info
 */
data class Currency(val code: String?, val name: String?, val symbol: String?)
