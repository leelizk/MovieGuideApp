package com.example.movieguideapp.data.local


import com.example.movieguideapp.data.model.CountryResource
import io.reactivex.Single

interface CountryDataSource {
    fun getCountries(): Single<List<CountryResource>>
}