package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData

import com.example.movieguideapp.data.model.Album

class AlbumDetailViewModel(application: Application) : BaseViewModel(application){

    val album: MutableLiveData<Album>?=null;

    private val TAG:String=AlbumDetailViewModel::class::java.javaClass.simpleName

    fun onActivityCreated(_album:Album?=null) {
        album?.value = _album;
    }


    fun play(context: Context, view: View){
        Log.i(TAG,"play ==>")
    }

}