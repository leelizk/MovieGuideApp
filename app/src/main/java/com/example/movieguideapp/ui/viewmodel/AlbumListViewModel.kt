package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieguideapp.data.vo.AlbumItem
import com.example.movieguideapp.data.vo.AlbumTwoItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AlbumListViewModel(application: Application): BaseViewModel(application){

    private val _albumListData : MutableLiveData<List<AlbumTwoItem>> = MutableLiveData<List<AlbumTwoItem>>();
    //动态数据
    val albumListData: MutableLiveData<List<AlbumTwoItem>> = _albumListData;

    fun onActivityCreated() {
        loadData();
    }


    /**
     *  获取数据
     */
    fun getAlbumListData() : LiveData<List<AlbumTwoItem>> {
        return albumListData
    }

    var myList: List<AlbumItem> = listOf();
    //加载测试数据
    fun loadData(){
        GlobalScope.launch  {
            var list:MutableList<AlbumItem> = mutableListOf<AlbumItem>();
            for(i in 1..11){
                var item:AlbumItem=AlbumItem(i,"测试"+i,"")
                list.add(item)
            }
            myList = list;
            //刷新数据
            _albumListData.postValue(buildItems())
        }
    }

    private fun buildItems(): List<AlbumTwoItem>{

            var pageSize = 2;
            var list: MutableList<AlbumTwoItem> = mutableListOf<AlbumTwoItem>();
            var size:Int = myList.size;
            for ((index, str) in myList.withIndex()) {

                    var newIndex: Int = index * pageSize;
                    var nextIndex: Int = newIndex + 1;
                    if(newIndex < size) {
                        var itemOne: AlbumItem? = myList?.get(newIndex);
                        var itemTwo: AlbumItem? = null;

                        if (nextIndex < size) {
                            itemTwo = myList?.get(nextIndex);
                        }
                        list.add(AlbumTwoItem(itemOne, itemTwo))
                    }

            }
        return list;
    }


}