package com.rebtel.country.list

import android.content.SharedPreferences
import com.rebtel.country.data.local.LocalDataSource
import com.rebtel.country.data.model.CountryResponseDataModel
import com.rebtel.country.network.ApiService
import com.rebtel.country.ui.country.list.CountryListVM
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import rx.observers.TestSubscriber
import javax.inject.Inject

class CountryFetchTest {

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var sp: SharedPreferences

    @Mock
    internal var editor: SharedPreferences.Editor? = null

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var countryListVM: CountryListVM

    private var testSubscriber: TestSubscriber<Response<CountryResponseDataModel>>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        countryListVM = CountryListVM(sp, apiService)
        testSubscriber = TestSubscriber.create<Response<CountryResponseDataModel>>()
    }

    @Test
    fun testFetchCountryList() {
        countryListVM.fetchAllCountries()
    }
}