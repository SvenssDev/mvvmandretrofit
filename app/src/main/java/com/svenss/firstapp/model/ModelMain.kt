package com.svenss.firstapp.model

/**
 * Created by miguelleon on 12,octubre,2021
 */

data class ResponseMain(
    val country_list: MutableList<Country>
)

data class Country(
    val country_image: String,
    val country_name: String
)