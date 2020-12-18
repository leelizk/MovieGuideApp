package com.example.movieguideapp.ui.vo

import android.view.View

data class AlbumItem(
    val index:Int?=0,
    val itemName:String?="demo",
    val imageUrl:String?="nothing",
    val onClick: ((view: View) -> Unit)? = null
)