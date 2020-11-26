package com.example.movieguideapp.ui.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.movieguideapp.data.vo.AlbumTwoItem


class AlbumListAdapter<T>(
    private val context: Context

): RecyclerView.Adapter<AlbumListAdapter.ViewHolder>(){


    private val layoutInflater = LayoutInflater.from(context)

    private var items: List<AlbumTwoItem> = emptyList()



    //更新adapter
    fun updateItems(items: List<AlbumTwoItem>) {
        this.items = items
        //必须
        notifyDataSetChanged()
    }

    // 这是搞什么飞机？？？ itemViewType有什么用?
    override fun getItemViewType(position: Int): Int = items[position].layoutId


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 必须实现上面的 getItemViewType
        val item = items.first { it.layoutId == viewType }
        return item.createViewHolder(layoutInflater, parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
       return items.size
    }


    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: Item)
    }

    abstract class Item {
        abstract val layoutId: Int
        abstract fun provideViewHolder(binding: ViewDataBinding): ViewHolder
        fun createViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): ViewHolder {
            return provideViewHolder(DataBindingUtil.inflate(layoutInflater, layoutId, parent, false))
        }
    }


}