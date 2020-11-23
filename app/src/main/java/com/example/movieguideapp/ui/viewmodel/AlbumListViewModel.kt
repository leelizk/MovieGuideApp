package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieguideapp.data.vo.AlbumItem
import com.example.movieguideapp.data.vo.AlbumTwoItem
import kotlin.concurrent.thread


class AlbumListViewModel(application: Application): BaseViewModel(application){

    //动态数据
    val albumListData: MutableLiveData<List<AlbumTwoItem>> by lazy {
        MutableLiveData<List<AlbumTwoItem>>().also {
            //加载数据
            loadData()
        }
    }


    /**
     *  获取数据
     */
    fun getAlbumListData() : LiveData<List<AlbumTwoItem>> {
        return albumListData
    }


    //加载测试数据
    private fun loadData(){
        thread(start = true) {
            var list:MutableList<AlbumTwoItem> = mutableListOf<AlbumTwoItem>();
            for(i in 1..10){
                val item = AlbumTwoItem(
                    AlbumItem(i,"测试"+i,""),
                    AlbumItem(i+1,"测试"+(i+1),"")
                )

                list.add(item)
            }
            //刷新数据
            albumListData.postValue(list)
        }
    }


}