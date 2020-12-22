package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.movieguideapp.R
import com.example.movieguideapp.R.id.container
import com.example.movieguideapp.base.App
import com.example.movieguideapp.base.common.schedulers.BaseSchedulerProvider
import com.example.movieguideapp.base.extension.with
import com.example.movieguideapp.data.AlbumRepository
import com.example.movieguideapp.data.model.Album
import com.example.movieguideapp.data.remote.AlbumApi
import com.example.movieguideapp.data.remote.MovieConstants
import com.example.movieguideapp.data.remote.results.DiscoverResult
import com.example.movieguideapp.data.remote.results.Page
import com.example.movieguideapp.ui.vo.AlbumItem
import com.example.movieguideapp.ui.vo.AlbumTwoItem
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AlbumListViewModel(application: Application,
                         private val albumRepository: AlbumRepository,
                         private val schedulerProvider: BaseSchedulerProvider
): BaseViewModel(application){

    companion object {
        const val BASE_IMG_URL_250_PX = "https://github.com/hjnilsson/country-flags/blob/master/png250px/"

        //w500/original can replace
        //filetype .jpg,.png, .svg
        const val BASE_IMG_W500_PREFIX = "https://image.tmdb.org/t/p/w500/";
    }
    private lateinit var navController: NavController;



    var albums: List<Album>? = listOf();
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
    //加载测试数据
    fun loadData(){
        try {
            rxLaunch {

                var params: Map<String, String> = mapOf<String, String>();
                albumRepository.getAll(true, params)
                    .with(schedulerProvider)
                    .subscribeBy(
                        onSuccess = {
                            albums = it;
                            //TODO 不能使用 postValue ?? 如果使用，不会刷新
                            //_albumListData.postValue()
                            _albumListData.value = convertItems()
                        },
                        onError = { _errorLiveData.value = it }
                    )

            }
        }catch (e:Exception){
            e.printStackTrace()
            Log.i("Exception==", e.message.toString())
        }
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
                        BASE_IMG_W500_PREFIX + tmp?.poster,
                    //onClick = {
                       // navController = Navigation.findNavController(getApplication<App>())
                       // parentFragment?.findNavController()?.navigate(command.actionId, command.args)
                    //}
                        onClick = {
                            //通过 xml 传参
                            //可以传当前的view 或者 context
                            go2Detail(it,tmp)
                        }
                );
                var itemTwo: AlbumItem? = null;

                if (nextIndex < size) {
                    var tmp2: Album? = albums?.get(nextIndex);
                    itemTwo = AlbumItem(nextIndex,tmp2?.title,
                            BASE_IMG_W500_PREFIX + tmp2?.poster,
                    onClick = {

                    });
                }
                list.add(AlbumTwoItem(itemOne, itemTwo))
            }
        }
        return list;
    }


    fun go2Detail(view: View, item:Album?=null){
        var b:Bundle = Bundle();
        b.putSerializable("album",item)
        Navigation.findNavController(view).navigate(R.id.album_detail_action,b)
    }

}