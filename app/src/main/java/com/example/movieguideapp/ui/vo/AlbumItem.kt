package com.example.movieguideapp.ui.vo

data class AlbumItem(
    val index:Int?=0,
    val itemName:String?="demo",
    val imageUrl:String?="nothing",
    val onClick: (() -> Unit)? = null
)