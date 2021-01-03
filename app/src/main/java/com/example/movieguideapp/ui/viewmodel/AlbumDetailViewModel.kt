package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.movieguideapp.data.local.AlbumDao
import com.example.movieguideapp.data.model.Album

class AlbumDetailViewModel(
    application: Application,
    val navigationViewModel: NavigationViewModel,
    val albumDao: AlbumDao
) : BaseViewModel(application) {

    private val _album: MutableLiveData<Album> = MutableLiveData<Album>();
    val album: MutableLiveData<Album>? = _album
    private val TAG: String = AlbumDetailViewModel::class::java.javaClass.simpleName

    fun loadData(albumId: Long?) {
        albumId.let {
            _album.value = albumDao.getById(albumId)
        }
    }


    fun play(context: Context, view: View) {

    }

}