package com.example.movieguideapp.ui.viewmodel

import com.example.movieguideapp.BuildConfig
import com.example.movieguideapp.base.utils.PlayUtil.Companion.DEBUG_URL
import com.example.movieguideapp.ui.fragment.AlbumDetailFragmentDirections
import com.example.movieguideapp.ui.fragment.AlbumFragmentDirections

class AlbumListNaviagtionViewModel : NavigationViewModel() {

    fun showDetail() {
        navigationCommandStream.value = NavigationCommand.ShowFragment(
            directions = AlbumFragmentDirections.albumDetailAction()
        )
    }

    fun showPlay(url: String) {
        var tmpUrl = url;
        if(BuildConfig.DEBUG){
            tmpUrl = DEBUG_URL
        }

        navigationCommandStream.value = NavigationCommand.ShowFragment(
            directions = AlbumDetailFragmentDirections.playerAction(tmpUrl)
        )
    }

}