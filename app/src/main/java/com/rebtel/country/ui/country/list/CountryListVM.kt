package com.rebtel.country.ui.country.list

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rebtel.country.data.model.CountryResponseDataModel
import com.rebtel.country.network.ApiService
import io.reactivex.Flowable
import javax.inject.Inject

class CountryListVM @Inject constructor(
    private val apiService: ApiService
) {

    val countriesLiveData = MutableLiveData<LiveData<List<CountryResponseDataModel>>>()

    val loadingLiveData = MutableLiveData<Boolean>()

    var items: ArrayList<String>

    private val SIZE = 1000
    private var x = 0
    private var y = 0
    private val letters =
        arrayListOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R")

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
    var dataCountryList: List<CountryResponseDataModel>? = null

    /**
     * fetch all countries
     */
    fun fetchAllCountries(): Flowable<Unit> =
        apiService.getAllCountries()
            .map {
                if (it.isSuccessful) {
                    dataCountryList = it.body()!!

                    dataRetrieved = true
                }
            }

    /**
     * Set a progress dialog visible on the View
     * @param visible visible or not visible
     */
    fun setLoadingVisibility(visible: Boolean) {
        loadingLiveData.postValue(visible)
    }
}