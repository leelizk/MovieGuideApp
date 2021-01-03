package com.example.movieguideapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.movieguideapp.R
import com.example.movieguideapp.base.common.autoCleared
import com.example.movieguideapp.base.utils.PlayUtil
import com.example.movieguideapp.databinding.FragmentPlayerBinding
import com.example.movieguideapp.ui.viewmodel.MyPlayerViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyPlayerFragment : BaseFragment() {

    var mPlayerView: StyledPlayerView? = null

    private val viewModel: MyPlayerViewModel by viewModel();

    private var binding by autoCleared<FragmentPlayerBinding>()

    private lateinit var videoUrl: String;

    private lateinit var playUtil: PlayUtil;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false)
        binding.viewModel = viewModel
        playUtil = context?.let { PlayUtil(it) }!!
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //在这里设置liveData
        videoUrl = arguments?.getString("play_url").toString()
        val mediaItem: MediaItem = MediaItem.fromUri(videoUrl);
        binding.playerView.player?.setMediaItem(mediaItem)
        binding.playerView.player?.prepare()
        binding.playerView.player?.play()

    }


    override fun onDestroy() {
        super.onDestroy()
        binding?.playerView.player?.release()

    }


}