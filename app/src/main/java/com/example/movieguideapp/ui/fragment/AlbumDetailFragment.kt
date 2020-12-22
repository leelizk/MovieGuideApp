package com.example.movieguideapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.movieguideapp.R
import com.example.movieguideapp.base.common.autoCleared
import com.example.movieguideapp.data.model.Album
import com.example.movieguideapp.databinding.AlbumDtailBinding
import com.example.movieguideapp.databinding.FragmentAlbumListBinding
import com.example.movieguideapp.ui.viewmodel.AlbumDetailViewModel
import com.example.movieguideapp.ui.viewmodel.AlbumListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  相册详情
 */

class AlbumDetailFragment : BaseFragment(){

    private val viewModel: AlbumDetailViewModel by viewModel()

    private var binding by autoCleared<AlbumDtailBinding>()

    private val TAG:String=AlbumDetailFragment::class::java.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"111"+savedInstanceState?.getSerializable("album").toString())
        // savedInstanceState is null
        var album: Album = savedInstanceState?.getSerializable("album") as Album
        //加载数据与传参
        viewModel.onActivityCreated(album)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.album_dtail,
                container,
                false
        )

        var album: Album = savedInstanceState?.getSerializable("album") as Album
        //加载数据与传参
        viewModel.onActivityCreated(album)


        Log.d(TAG,"222"+savedInstanceState?.getSerializable("album").toString())
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //why savedInstanceState is null??

        
    }

    //TODO  fragment 如何传参
}