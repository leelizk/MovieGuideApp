package com.example.movieguideapp.data.remote


import com.example.movieguideapp.ui.vo.CountryResource
import io.reactivex.Single
import retrofit2.http.GET

interface CountryApiService {
    @GET("all")
    fun getCountries(): Single<List<CountryResource>>
}