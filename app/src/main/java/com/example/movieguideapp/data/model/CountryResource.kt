package com.example.movieguideapp.data.model

import com.google.gson.annotations.SerializedName

data class CountryResource(
    @field:SerializedName("alpha2Code")
    val alpha2Code: String? = null,

    @field:SerializedName("flag")
    val flag: String? = null,

    @field:SerializedName("population")
    val population: Int? = null,

    @field:SerializedName("name")
    val name: String? = null
)