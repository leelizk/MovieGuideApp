package com.example.movieguideapp.data.local.impl

import com.example.movieguideapp.base.common.schedulers.BaseSchedulerProvider
import com.example.movieguideapp.base.extension.with
import com.example.movieguideapp.data.local.AlbumDao
import com.example.movieguideapp.data.model.Album
import com.example.movieguideapp.data.remote.AlbumApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AlbumDaoImpl(
    private val albumDao:AlbumDao,
    private val albumApi:AlbumApi,
    private val schedulerProvider: BaseSchedulerProvider
) {
     fun getAll(force:Boolean): List<Album> {
         var retList:List<Album> = listOf();
        if(force){
            albumApi.getAll()
                .with(schedulerProvider)
                .doAfterSuccess {
                    retList = it.toList()
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