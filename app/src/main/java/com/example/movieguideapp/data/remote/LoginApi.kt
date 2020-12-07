package com.example.movieguideapp.data.remote

import retrofit2.http.GET

interface LoginApi {

    @GET("/authentication/session/convert/4")
    fun getSessionV4():Int

    @GET("/authentication/session/new")
    fun getSession():Int

}