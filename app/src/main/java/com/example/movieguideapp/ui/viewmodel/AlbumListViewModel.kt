package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieguideapp.data.vo.AlbumItem
import kotlin.concurrent.thread


class AlbumListViewModel(application: Application): AndroidViewModel(application){

    //动态数据
    private val albumListData: MutableLiveData<List<AlbumItem>> by lazy {
        MutableLiveData<List<AlbumItem>>().also {
            //加载数据
            loadData()
        }
    }


    /**
     *  获取数据
     */
    fun getAlbumListData() : LiveData<List<AlbumItem>> {
        return albumListData
    }


    //加载测试数据
    private fun loadData(){
        thread(start = true) {
            var list:MutableList<AlbumItem> = mutableListOf<AlbumItem>();
            for(i in 1..30){
                val item = AlbumItem(
                    i,"测试"+i,""
                )

                list.add(item)
            }
            //刷新数据
            albumListData.postValue(list)
        }
    }


}