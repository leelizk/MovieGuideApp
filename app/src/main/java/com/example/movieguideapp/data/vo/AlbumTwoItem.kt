package com.example.movieguideapp.data.vo

import androidx.databinding.ViewDataBinding
import com.example.movieguideapp.databinding.AlbumItemBinding
import com.example.movieguideapp.ui.adapter.AlbumListAdapter

data class AlbumTwoItem (
    var oneItem:AlbumItem?,
    var twoItem:AlbumItem?
): AlbumListAdapter.Item() {
    override val layoutId:  Int = com.example.movieguideapp.R.layout.album_item

    override fun provideViewHolder(binding: ViewDataBinding): AlbumListAdapter.ViewHolder {
        return AlbumTwoViewHolder(binding as AlbumItemBinding)
    }


    private class AlbumTwoViewHolder(
        private val binding: AlbumItemBinding
    ) : AlbumListAdapter.ViewHolder(binding.root) {

        override fun bind(item: AlbumListAdapter.Item) {
            binding.twoItem = item as AlbumTwoItem
            binding.executePendingBindings()
        }
    }
}

