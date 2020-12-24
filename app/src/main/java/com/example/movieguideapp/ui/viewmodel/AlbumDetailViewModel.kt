package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.movieguideapp.ui.vo.AlbumItem

class AlbumDetailViewModel(application: Application) : BaseViewModel(application){

    private val _album:MutableLiveData<AlbumItem> = MutableLiveData<AlbumItem>();
    val album: MutableLiveData<AlbumItem>?= _album

    private val TAG:String=AlbumDetailViewModel::class::java.javaClass.simpleName

    fun onActivityCreated(item:AlbumItem?) {
       _album.value = item;
    }



    fun play(context: Context, view: View){
        Log.i(TAG,"play ==>")
    }

}