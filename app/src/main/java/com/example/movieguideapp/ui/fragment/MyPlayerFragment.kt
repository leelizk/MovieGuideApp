package com.example.movieguideapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.movieguideapp.R
import com.example.movieguideapp.base.common.autoCleared
import com.example.movieguideapp.databinding.FragmentPlayerBinding
import com.google.android.exoplayer2.MediaItem


class MyPlayerFragment : BaseFragment() {

    private var binding by autoCleared<FragmentPlayerBinding>()

    private val args: MyPlayerFragmentArgs by navArgs()

    private lateinit var videoUrl: String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //在这里设置liveData
        videoUrl = args?.videoUrl.toString()
        Log.i("Player", " 播放地址 >>>  ${videoUrl}")
        val mediaItem: MediaItem = MediaItem.fromUri(videoUrl);
        binding.playerView.player?.setMediaItem(mediaItem)
        binding.playerView.player?.prepare()
        binding.playerView.player?.seekTo(0)
        binding.playerView.player?.play()
        Log.i("Player", "start====>")

    }


    override fun onDestroy() {
        super.onDestroy()
        binding.playerView.player?.release()

    }


}