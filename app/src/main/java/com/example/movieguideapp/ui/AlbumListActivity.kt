package com.example.movieguideapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.movieguideapp.R
import com.example.movieguideapp.data.vo.AlbumTwoItem
import com.example.movieguideapp.databinding.ActivityAlbumlistBinding
import com.example.movieguideapp.ui.adapter.AlbumListAdapter
import com.example.movieguideapp.ui.viewmodel.AlbumListViewModel
import com.example.movieguideapp.ui.viewmodel.BaseActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumListActivity : AppCompatActivity() {

    companion object{
        val TAG:String = AlbumListActivity::class.java.simpleName;
    }

    lateinit var mBinding:ActivityAlbumlistBinding;
    private val albumListVm:AlbumListViewModel by viewModel();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView(this, R.layout.activity_albumlist);
        var list:MutableList<AlbumTwoItem> = mutableListOf();
        initHandler(viewModel = albumListVm)

    }

    fun initHandler(viewModel: AlbumListViewModel){
                viewModel.getAlbumListData().observe(this,Observer{its->{
                    Log.d(TAG,"its ===> " + its);
                    var pageSize:Int = 2;
                    its.forEach { one->{

                    } }
                }
            })
    }
}