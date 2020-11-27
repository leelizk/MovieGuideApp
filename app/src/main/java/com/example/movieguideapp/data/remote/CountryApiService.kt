package com.example.movieguideapp.data.remote


import com.example.movieguideapp.data.model.CountryResource
import io.reactivex.Single
import org.checkerframework.common.reflection.qual.GetClass
import retrofit2.http.GET

interface CountryApiService {
    @GET("all")
    fun getCountries(): Single<List<CountryResource>>


    @GET("all2")
    fun getCountries2(): Single<List<CountryResource>>
}