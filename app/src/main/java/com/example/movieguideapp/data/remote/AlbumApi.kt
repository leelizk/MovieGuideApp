package com.example.movieguideapp.data.remote

import com.example.movieguideapp.data.model.Album

//网络接口

interface AlbumApi {

    fun getAll():List<Album>
}