package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movieguideapp.base.utils.PlayUtil


class MyPlayerViewModel(application: Application, playUtil: PlayUtil) : BaseViewModel(application) {


    private val _playList: MutableLiveData<MutableList<String>> = MutableLiveData<MutableList<String>>();

    private val TAG: String = MyPlayerViewModel::class.java.simpleName.toString()

    var playList: MutableLiveData<MutableList<String>> = _playList;

    var myPlayUtil: PlayUtil = playUtil;

    fun onActivityCreated() {

    }

    fun addToPlay(url: String) {
        _playList.value?.add(url)
    }

    fun addToPlay(list: List<String>) {
        _playList.value?.addAll(list);
    }

    fun getPlayUtil(): PlayUtil {
        return myPlayUtil;
    }

    fun playOne(): String {
        var aUrl: String = _playList?.value?.get(0).toString()
        Log.i(TAG, "playing url :  " + aUrl);
        return aUrl;
    }

}