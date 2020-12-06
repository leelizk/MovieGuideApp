package com.example.movieguideapp.data.remote

import com.example.movieguideapp.data.model.Photo
import io.reactivex.Single

interface PhotoApi {

    fun getByAlbumId(albumId:String?): Single<List<Photo>>
}