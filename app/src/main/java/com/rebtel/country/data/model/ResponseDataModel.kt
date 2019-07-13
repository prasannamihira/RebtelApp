package com.rebtel.country.data.model

data class CountryResponseDataModel(
    val name: String?,
    var alpha2Code: String?,
    val alpha3Code: String?,
    val capital: String?,
    val region: String?,
    val subregion: String?,
    val population: Int?,
    val nativeName: String?,
    val currencies: Array<Currencie>,
    val timezones: Array<String>,
    val flag: String?
)

data class Currencie(val code: String?, val name: String?, val symbol: String?)
