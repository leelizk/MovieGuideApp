package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.movieguideapp.base.common.schedulers.BaseSchedulerProvider
import com.example.movieguideapp.base.extension.with
import com.example.movieguideapp.data.AlbumRepository
import com.example.movieguideapp.data.model.Album
import io.reactivex.rxkotlin.subscribeBy

class AlbumDetailViewModel(
        application: Application,
        val navigationViewModel: AlbumListNavigationViewModel,
        private val albumRepository: AlbumRepository,
        private val schedulerProvider: BaseSchedulerProvider,
) : BaseViewModel(application) {

    private val _album: MutableLiveData<Album> = MutableLiveData<Album>();
    val album: MutableLiveData<Album>? = _album
    private val TAG: String = AlbumDetailViewModel::class::java.javaClass.simpleName

    fun loadData(albumId: Long?) {
        Log.i(TAG, "loadData ==> $albumId")
            rxLaunch {
                albumRepository.getById(albumId!!)
                        .with(schedulerProvider)
                        .subscribeBy(
                                onSuccess = {
                                    Log.i("album", "test >>> ${it.title} , ${it.poster}")
                                    //为什么没有刷新?
                                    _album?.value = it;
                                },
                                onError = { _errorLiveData.value = it }
                        )
            }
    }


    fun play(context: Context, view: View) {
        navigationViewModel.showPlay(album?.value?.poster.toString())
    }

}