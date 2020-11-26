package com.example.movieguideapp.data.local

import com.example.movieguideapp.data.remote.CountryApiService

class CountryRepository (
    private val countryApiService: CountryApiService
) : CountryDataSource {
    override fun getCountries() = countryApiService.getCountries()
}