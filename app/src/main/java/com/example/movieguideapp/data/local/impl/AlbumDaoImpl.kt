package com.example.movieguideapp.data.local.impl

import android.util.Log
import com.example.movieguideapp.base.common.schedulers.BaseSchedulerProvider
import com.example.movieguideapp.base.extension.with
import com.example.movieguideapp.data.local.AlbumDao
import com.example.movieguideapp.data.model.Album
import com.example.movieguideapp.data.remote.AlbumApi
import com.example.movieguideapp.data.remote.MovieConstants
import com.example.movieguideapp.data.remote.results.DiscoverResult
import com.example.movieguideapp.data.remote.results.Page
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.cast
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.AsyncSubject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AlbumDaoImpl(
    private val albumDao:AlbumDao,
    private val albumApi:AlbumApi,
    private val schedulerProvider: BaseSchedulerProvider
) {

    var page: Page<DiscoverResult>? = null;
     fun getAll(force:Boolean,params:Map<String,String>): Single<List<Album>> {
        if(force){
            page = albumApi.popluarPage(MovieConstants.API_KEY, params)
                    .with(schedulerProvider)
                   .blockingGet()
            var list:List<Album> =  covertList();

            GlobalScope.launch {
                albumDao.insertAll(list);
            }

            return Single.create{
                    list;
            };
        }else{
            var list:List<Album> =  albumDao.getAll()
            Log.i("TEST","===============>"+list.size)
            return Single.create {
                list
            }
        }
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