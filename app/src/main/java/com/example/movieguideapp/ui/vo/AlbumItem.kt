package com.example.movieguideapp.ui.vo

import android.content.Context
import android.view.View
import java.io.Serializable

data class AlbumItem(
    val index:Int?=0,
    val itemName:String?="demo",
    val imageUrl:String?="nothing",
    val onClick: ((view: View) -> Unit)? = null
):Serializable