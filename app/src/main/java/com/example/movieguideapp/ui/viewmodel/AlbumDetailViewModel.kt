package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import com.example.movieguideapp.R
import com.example.movieguideapp.base.utils.PlayUtil
import com.example.movieguideapp.ui.vo.AlbumItem
import com.google.android.exoplayer2.ui.StyledPlayerView

class AlbumDetailViewModel(application: Application,playUtil:PlayUtil) : BaseViewModel(application){

    private val _album:MutableLiveData<AlbumItem> = MutableLiveData<AlbumItem>();
    val album: MutableLiveData<AlbumItem>?= _album

    private var  myPlayUtil: PlayUtil = playUtil;
    private val TAG:String=AlbumDetailViewModel::class::java.javaClass.simpleName

    fun onActivityCreated(item:AlbumItem?) {
       _album.value = item;
    }



    fun play(context: Context, view: View){
        Log.i(TAG,"play ==>")
        // 为什么buildConfig 获取不到field
         var pview = view.findViewById<StyledPlayerView>(R.id.player_view)
        // myPlayUtil.playHls(pview,PlayUtil.DEBUG_URL);
        //怎么显示？？
         Navigation.findNavController(view).navigate(R.id.player_action);
    }

}