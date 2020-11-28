package com.example.movieguideapp.data.vo

import androidx.databinding.BaseObservable

data class AlbumItem(
    val index:Int?=0,
    val itemName:String?="demo",
    val imageUrl:String?="nothing",
    val onClick: (() -> Unit)? = null
): BaseObservable()