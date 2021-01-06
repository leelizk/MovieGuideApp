package com.example.movieguideapp.base.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.example.movieguideapp.ui.viewmodel.AlbumListViewModel.Companion.BASE_IMG_W500_PREFIX

/**
 * 在xml中可以自动转换 imageView 并缓存
 */

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
        val options = RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(view.context).load(BASE_IMG_W500_PREFIX + url)
                .transition(withCrossFade())
                .apply(options)
                .into(view)
    }
}