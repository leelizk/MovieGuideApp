package com.example.movieguideapp.data.local.impl

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
     fun getAll(force:Boolean,params:Map<String,String>): List<Album> {
         var retList:List<Album> = listOf();
         var page: Page<DiscoverResult>? = null;
        if(force){
            albumApi.popluarPage(MovieConstants.API_KEY,params)
                .with(schedulerProvider)
                .doAfterSuccess {
                    page = it
                }.doFinally {
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
}