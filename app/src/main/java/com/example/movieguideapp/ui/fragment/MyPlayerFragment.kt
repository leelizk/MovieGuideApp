package com.example.movieguideapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.movieguideapp.R
import com.example.movieguideapp.base.common.autoCleared
import com.example.movieguideapp.base.utils.PlayUtil
import com.example.movieguideapp.base.utils.PlayUtil.Companion.DEBUG_URL
import com.example.movieguideapp.databinding.FragmentPlayerBinding
import com.example.movieguideapp.ui.viewmodel.MyPlayerViewModel
import com.google.android.exoplayer2.ui.StyledPlayerView
import kotlinx.android.synthetic.main.fragment_player.*
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
        videoUrl = arguments?.getString("play_url").toString()
        viewModel.addToPlay(videoUrl)
        player_view.player = playUtil.getPlayer()
        // var tmpUrl:String = viewModel.playOne();
        playUtil.playHls(DEBUG_URL);

    }


}