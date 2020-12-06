package com.example.movieguideapp.ui.fragment

import android.os.Bundle
import com.example.movieguideapp.ui.viewmodel.AlbumDetailViewModel
import com.example.movieguideapp.ui.viewmodel.AlbumListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  相册详情
 */

class AlbumDetailFragment : BaseFragment(){

    private val viewModel: AlbumDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //TODO  fragment 如何传参
}