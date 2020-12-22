package com.example.movieguideapp.ui.viewmodel

import android.app.Application

import com.example.movieguideapp.data.model.Album

class AlbumDetailViewModel(application: Application) : BaseViewModel(application){

    var album:Album?=null;

    fun onActivityCreated() {

    }

}