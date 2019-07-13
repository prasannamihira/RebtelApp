package com.rebtel.country.network

import com.rebtel.country.data.model.CountryResponseDataModel
import com.rebtel.country.util.Config
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.*

/**
 * Rebtel app api services
 */
interface ApiService {

    /**
     * fetch all the countries which api return
     */
    @Headers(Config.CONTENT_TYPE_JSON)
    @GET("all")
    fun getAllCountries(): Flowable<Response<List<CountryResponseDataModel>>>

    /**
     * fetch the country information by country code
     * @param code
     */
    @Headers(Config.CONTENT_TYPE_JSON)
    @POST("alpha/{code}")
    fun getCountryByCountryCode(@Path("code") code: String): Flowable<Response<CountryResponseDataModel>>

}