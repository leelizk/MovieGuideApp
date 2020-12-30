package com.example.movieguideapp.ui.fragment

import android.os.Bundle
import com.example.movieguideapp.base.common.autoCleared
import com.example.movieguideapp.databinding.FragmentAlbumListBinding

class MyPlayerFragment : BaseFragment() {


    private var binding by autoCleared<FragmentAlbumListBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


}