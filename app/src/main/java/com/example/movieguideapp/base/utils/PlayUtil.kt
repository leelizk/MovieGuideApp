package com.example.movieguideapp.base.utils

import android.content.Context
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class PlayUtil(context: Context){

    //private val player by lazy { ExoPlayerFactory.newSimpleInstance(applicationContext) }
    private var INSTANCE: SimpleExoPlayer? = null
    private var myContext:Context = context;
    init {
        if (INSTANCE == null) {
            INSTANCE = SimpleExoPlayer.Builder(context).build()
        }
    }


    fun playHls(_uri: String) {
        //check uri support or not
        INSTANCE?.setMediaItem(MediaItem.fromUri(_uri));
        INSTANCE?.prepare();
        INSTANCE?.play();
    }

    fun playFile(path: String){
        //check File support or not
        val mediaItem: MediaItem = MediaItem.fromUri(path)
        play(mediaItem)
    }

    private fun play(mediaItem: MediaItem) {
        INSTANCE?.setMediaItem(mediaItem)
        INSTANCE?.prepare();
        INSTANCE?.play();
    }


    fun getPlayer(): SimpleExoPlayer? {
        return INSTANCE;
    }


    companion object {
        const val DEBUG_URL = "http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8";
        const val UA = "ua"
    }

}