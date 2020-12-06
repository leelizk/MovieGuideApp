package com.example.movieguideapp.data.remote

import com.example.movieguideapp.data.model.Album
import io.reactivex.Single
import retrofit2.http.GET

//网络接口
interface AlbumApi {

    @GET("all")
    fun getAll(): Single<List<Album>>
}