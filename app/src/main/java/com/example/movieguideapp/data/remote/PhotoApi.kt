package com.example.movieguideapp.data.remote

import com.example.movieguideapp.data.model.Album
import com.example.movieguideapp.data.model.Photo

interface PhotoApi {

    fun getByAlbumId(albumId:String?):List<Photo>
}