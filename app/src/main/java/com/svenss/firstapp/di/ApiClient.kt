package com.svenss.firstapp.di

import com.svenss.firstapp.model.ResponseMain
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by miguelleon on 12,octubre,2021
 */

interface ApiClient {

    /** Function to get service */
    @GET("/v1/countries/1")
    fun getCountries(): Call<ResponseMain>
}