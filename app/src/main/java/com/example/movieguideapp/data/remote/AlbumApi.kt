package com.example.movieguideapp.data.remote

import com.example.movieguideapp.data.model.Album
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

//网络接口
interface AlbumApi {

    @GET("all")
    fun getAll(): Single<MutableList<Album>>





    //流行电影列表
    @GET("/discover/movie?sort_by=popularity.desc")
    fun popluarList(@Query("api_key")token: String, @QueryMap body: Map<String, String>):Single<MutableList<Album>>

}