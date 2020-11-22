package com.hinge.countryexplorer.common.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
        val options = RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(view.context).load(url)
            .transition(withCrossFade())
            .apply(options)
            .into(view)
    }
}