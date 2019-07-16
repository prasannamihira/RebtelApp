package com.rebtel.country.list

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.rebtel.country.data.model.CountryResponseDataModel
import com.rebtel.country.network.ApiService
import com.rebtel.country.ui.country.list.CountryListVM
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observer
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response
import rx.observers.TestSubscriber
import java.net.SocketException
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.junit.Rule



@RunWith(JUnit4::class)
class CountryFetchTest {

    @Rule
    var rule = MockitoJUnit.rule()

    @Mock
    lateinit var apiService: ApiService


    lateinit var countryListVM: CountryListVM

    private var testSubscriber: TestSubscriber<Response<CountryResponseDataModel>>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        countryListVM = CountryListVM(apiService)
        testSubscriber = TestSubscriber.create<Response<CountryResponseDataModel>>()
    }

    @Test
    fun testFetchCountryList() {

        // Mock API response
        Mockito.`when`(this.apiService.getAllCountries()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.anyList<CountryResponseDataModel>())
        }

        // Attacch fake observer
        val observer = mock(Flowable::class.java) as Flowable<LiveData<List<CountryResponseDataModel>>>
        this.countryListVM.countriesLiveData.observeForever { observer }

        // Invoke
        this.countryListVM.fetchAllCountries()

        // Verify
        assertNotNull(this.countryListVM.dataCountryList)
    }

    @Test
    fun fetchRepositories_error() {
        // Mock response with error
        Mockito.`when`(this.apiService.getAllCountries()).thenAnswer {
            return@thenAnswer Maybe.error<SocketException>(SocketException("No network here"))
        }

        // Invoke
        this.countryListVM.fetchAllCountries()

        // Assertions
        assertNotNull(this.countryListVM.dataCountryList)
        assert(this.countryListVM.dataCountryList.orEmpty() is Throwable)
    }

    @Test
    fun setLoadingVisibility_onNoData() {
        // Mock Response
        Mockito.`when`(this.apiService.getAllCountries()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.anyList<CountryResponseDataModel>())
        }
        // Spied ViewModel
        val spiedViewModel = com.nhaarman.mockitokotlin2.spy(this.countryListVM)

        // Invoke
        spiedViewModel.fetchAllCountries()

        // Verify
        verify(spiedViewModel, times(2)).setLoadingVisibility(ArgumentMatchers.anyBoolean())
    }

}