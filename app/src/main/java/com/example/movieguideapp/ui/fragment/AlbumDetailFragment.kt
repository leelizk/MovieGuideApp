package com.example.movieguideapp.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.movieguideapp.R
import com.example.movieguideapp.base.common.autoCleared
import com.example.movieguideapp.databinding.AlbumDtailBinding
import com.example.movieguideapp.ui.viewmodel.AlbumDetailViewModel
import com.example.movieguideapp.ui.vo.AlbumItem
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  相册详情
 */

class AlbumDetailFragment : BaseFragment() {

    private val viewModel: AlbumDetailViewModel by viewModel()

    private var binding by autoCleared<AlbumDtailBinding>()

    private  var album:AlbumItem? = null;

    private val TAG: String = AlbumDetailFragment::class::java.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.album_dtail,
                container,
                false
        )
        album = arguments?.getSerializable("albumItem") as AlbumItem
        viewModel.onActivityCreated(album);
        binding.abItemName1.text = viewModel.album?.value?.itemName;
       // var uri:Uri =  URI(viewModel?.album?.value?.imageUrl)
       // binding.abImage1

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLiveData()

        //加载数据与传参
        updateItem(album);
    }

    private fun initLiveData(){
        //这里如何刷新？
        //viewModel.album?.observe(viewLifecycleOwner, Observer{
            //update ui ?? 这个要怎么写?
            //viewModel.album.value.imageUrl = it.imageUrl
        //})
    }

    //TODO  fragment 如何传参

    private fun updateItem(item:AlbumItem?=null){
       viewModel?.onActivityCreated(item);
    }
}