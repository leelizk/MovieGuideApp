package com.example.movieguideapp.base.utils

import android.content.Context
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class Player private constructor(context:Context){

    fun playHls(uri:String){
        //check uri support or not
        val mediaItem: MediaItem = MediaItem.fromUri(uri)
        play(mediaItem)
    }

    fun playFile(path:String){
        //check File support or not
        val mediaItem: MediaItem = MediaItem.fromUri(path)
        play(mediaItem)
    }

    private fun play(mediaItem: MediaItem){
        INSTANCE?.setMediaItem(mediaItem)
        INSTANCE?.prepare();
        INSTANCE?.play();
    }




    companion object {
        private var INSTANCE: SimpleExoPlayer? = null
        @Synchronized fun getInstance(context:Context):SimpleExoPlayer{
            if (INSTANCE == null) {
                INSTANCE = SimpleExoPlayer.Builder(context).build()
            }
            return INSTANCE as SimpleExoPlayer
        }
    }

}