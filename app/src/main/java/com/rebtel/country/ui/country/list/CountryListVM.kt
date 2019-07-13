package com.rebtel.country.ui.country.list

import android.content.SharedPreferences
import com.rebtel.country.data.model.CountryResponseDataModel
import com.rebtel.country.network.ApiService
import javax.inject.Inject

class CountryListVM @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val apiService: ApiService
) {

    val results: ArrayList<String> = ArrayList()

    var items: ArrayList<String>

    private val SIZE = 1000
    private var x = 0
    private var y = 0
    val letters =
        arrayListOf<String>("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R")

    init {
        x = 0
        items = ArrayList<String>()
        val format = "%s Item %d"
        for (i in 0 until SIZE) {

            val letter = letters[x]
            if (y == 99) {
                x++
                y = 0
            }

            y++
            items.add(String.format(format, letter, i + 1))
        }
    }

    var dataRetrieved: Boolean = false
    lateinit var dataCountryList: List<CountryResponseDataModel>

    /**
     * fetch all countries
     */
    fun fetchAllCountries() =
        apiService.getAllCountries()
            .map {
                if (it.isSuccessful) {
                    dataCountryList = it.body()!!

                    dataRetrieved = true
                } else {

                }
            }
}