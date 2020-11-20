package com.example.movieguideapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.movieguideapp.R
import com.example.movieguideapp.data.vo.AlbumTwoItem
import com.example.movieguideapp.databinding.ActivityAlbumlistBinding
import com.example.movieguideapp.ui.adapter.AlbumListAdapter
import com.example.movieguideapp.ui.viewmodel.AlbumListViewModel

class AlbumListActivity : AppCompatActivity() {

    lateinit var mBinding:ActivityAlbumlistBinding;
    lateinit var albumListVm:AlbumListViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView(this, R.layout.activity_albumlist);
        albumListVm = ViewModelProviders.of(this).get(AlbumListViewModel::class.java);
        var list:MutableList<AlbumTwoItem> = mutableListOf();

        //what is todo
        //mBinding.myAdapter = AlbumListAdapter(this,list,R.layout.album_item,twoItem);
    }
}