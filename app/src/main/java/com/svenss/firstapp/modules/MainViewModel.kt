package com.svenss.firstapp.modules

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.svenss.firstapp.di.ApiClient
import com.svenss.firstapp.model.Country
import com.svenss.firstapp.model.ResponseMain
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by miguelleon on 12,octubre,2021
 */

class MainViewModel: ViewModel() {

    companion object{
        /** Url base to consume service */
        private const val URL_BASE = "https://616501fb09a29d0017c88efa.mockapi.io/"

        /** OkHttp for control times to consume service, spend response service */
        private val OK_HTTP = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .build()
    }

    /** Live Data */
    val listCountries: MutableLiveData<MutableList<Country>> by lazy { MutableLiveData<MutableList<Country>>() }


    /** Service list countries */
    fun startServiceCountries(){
        val retrofit = getRetrofit()

        /** This is a basic form to implement consume service with Retrofit */
        retrofit.getCountries().enqueue(object: Callback<ResponseMain>{
            override fun onResponse(call: Call<ResponseMain>, response: Response<ResponseMain>) {
                /** If response is success bind response and set information on Live Data */
                if (response.isSuccessful){
                    response.body()?.country_list?.let {
                        listCountries.value = it
                    }
                }
            }

            override fun onFailure(call: Call<ResponseMain>, t: Throwable) {
                /** Error on Endpoint implementation */
                print(t.localizedMessage)
            }
        })
    }

    /** Init Retrofit for implement */
    private fun getRetrofit() =
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OK_HTTP)
            .build()
            .create(ApiClient::class.java)
}