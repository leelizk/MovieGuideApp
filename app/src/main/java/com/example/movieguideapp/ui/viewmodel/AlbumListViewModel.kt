package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieguideapp.base.common.schedulers.BaseSchedulerProvider
import com.example.movieguideapp.base.extension.with
import com.example.movieguideapp.data.model.Album
import com.example.movieguideapp.data.remote.AlbumApi
import com.example.movieguideapp.ui.vo.AlbumItem
import com.example.movieguideapp.ui.vo.AlbumTwoItem
import com.example.movieguideapp.ui.vo.CountryResource
import io.reactivex.rxkotlin.subscribeBy


class AlbumListViewModel(application: Application,
                         private val albumApi: AlbumApi,
                         private val schedulerProvider: BaseSchedulerProvider
): BaseViewModel(application){

    companion object {
        const val BASE_IMG_URL_250_PX = "https://github.com/hjnilsson/country-flags/blob/master/png250px/"
    }

    var albums: MutableList<Album>? = mutableListOf();
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
        rxLaunch {
            //?
            albumApi.getAll()
                    .with(schedulerProvider)
                    .subscribeBy(
                            onSuccess = {
                                albums = it
                                _albumListData.postValue(convertItems())
                            },
                            onError = { _errorLiveData.value = it }
                    )

        }
        /*GlobalScope.launch  {

            //这里执行 http请求
            //TODO


            var list:MutableList<AlbumItem> = mutableListOf<AlbumItem>();
            for(i in 1..17){
                var item:AlbumItem=AlbumItem(i,"测试"+i,"")
                list.add(item)
            }
            myList = list;
            //刷新数据
            _albumListData.postValue(buildItems())
        }*/
    }

    private fun convertItems():List<AlbumTwoItem>{
        var pageSize = 2;
        var list: MutableList<AlbumTwoItem> = mutableListOf<AlbumTwoItem>();
        var size:Int? = albums?.size;
        for ((index, cr) in albums?.withIndex()!!) {

            var newIndex: Int = index * pageSize;
            var nextIndex: Int = newIndex + 1;
            if(newIndex < size!!) {
                var tmp: Album? = albums?.get(newIndex);
                var itemOne: AlbumItem? = AlbumItem(newIndex,tmp?.title,
                    tmp?.remark,
                    onClick = {
                       // parentFragment?.findNavController()?.navigate(command.actionId, command.args)
                    }
                );
                var itemTwo: AlbumItem? = null;

                if (nextIndex < size) {
                    var tmp2: Album? = albums?.get(nextIndex);
                    itemTwo = AlbumItem(nextIndex,tmp2?.title,
                        tmp2?.remark,
                    onClick = {

                    });
                }
                list.add(AlbumTwoItem(itemOne, itemTwo))
            }

        }
        return list;
    }

    private fun onImageItemClick( tmp: CountryResource) {

    }

    //恩成 一行两个的显示方式
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