package com.example.movieguideapp.ui.viewmodel

import com.example.movieguideapp.ui.fragment.AlbumDetailFragmentDirections
import com.example.movieguideapp.ui.fragment.AlbumFragmentDirections

class AlbumListNaviagtionViewModel : NavigationViewModel() {

    fun showDetail() {
        navigationCommandStream.value = NavigationCommand.ShowFragment(
            directions = AlbumFragmentDirections.albumDetailAction()
        )
    }

    fun showPlay(url: String) {
        navigationCommandStream.value = NavigationCommand.ShowFragment(
            directions = AlbumDetailFragmentDirections.playerAction(url)
        )
    }

}