package com.example.movieguideapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


class AlbumListAdapter<T>(
    private val context: Context, private val list: List<T>,
    private val layoutId: Int, private val variableId: Int

):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var binding: ViewDataBinding? = null
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false)
        } else {
            binding = DataBindingUtil.getBinding(convertView)
        }
        binding!!.setVariable(variableId, list[position])
        return binding.root
    }

    override fun getItem(p0: Int): Any {
       return list[p0]!!
    }

    override fun getItemId(p0: Int): Long {
       return p0.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }


}