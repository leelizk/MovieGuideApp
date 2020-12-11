package com.example.movieguideapp.data.local.impl

import androidx.lifecycle.MutableLiveData
import com.example.movieguideapp.base.common.schedulers.BaseSchedulerProvider
import com.example.movieguideapp.base.extension.with
import com.example.movieguideapp.data.local.AlbumDao
import com.example.movieguideapp.data.model.Album
import com.example.movieguideapp.data.remote.AlbumApi
import com.example.movieguideapp.data.remote.MovieConstants
import com.example.movieguideapp.data.remote.results.DiscoverResult
import com.example.movieguideapp.data.remote.results.Page
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AlbumDaoImpl(
    private val albumDao:AlbumDao,
    private val albumApi:AlbumApi,
    private val schedulerProvider: BaseSchedulerProvider
) {
    var retList:List<Album> = listOf()
    var page: Page<DiscoverResult>? = null;
     fun getAll(force:Boolean,params:Map<String,String>): List<Album> {
        if(force){
            //异步
            var tmpList:List<Album> = listOf();
            albumApi.popluarPage(MovieConstants.API_KEY,params)
                .with(schedulerProvider)
                .doAfterSuccess {
                    page = it
                    covertList();
                }.doFinally {
                        //写入缓存
                        albumDao.deleteAll();
                        GlobalScope.launch {
                            albumDao.insertAll(retList);
                        }
                }
        }else{
            retList =  albumDao.getAll();
        }
         return retList;
    }

    fun covertList():List<Album>{
        var albumList:MutableList<Album> = mutableListOf();
        page?.results?.forEachIndexed { index, discoverResult ->
               println("index : ${index}, discoverResult : ${discoverResult}");
            var album:Album = Album(index,discoverResult.title,"",
                            discoverResult?.overview,"",
                    0,"",discoverResult.posterPath)
            albumList.add(album);
        }
        return albumList.toList()
    }
}