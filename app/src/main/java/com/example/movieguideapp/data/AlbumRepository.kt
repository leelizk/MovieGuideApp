package com.example.movieguideapp.data

import com.example.movieguideapp.base.common.schedulers.BaseSchedulerProvider
import com.example.movieguideapp.base.extension.with
import com.example.movieguideapp.data.local.AlbumDao
import com.example.movieguideapp.data.model.Album
import com.example.movieguideapp.data.remote.AlbumApi
import com.example.movieguideapp.data.remote.MovieConstants
import com.example.movieguideapp.data.remote.results.DiscoverResult
import com.example.movieguideapp.data.remote.results.Page
import io.reactivex.Single

class AlbumRepository(
        private val albumApi: AlbumApi,
        private val albumDao: AlbumDao,
        private val schedulerProvider: BaseSchedulerProvider
) {


    fun getAll(force: Boolean, params: Map<String, String>): Single<List<Album>> {
        return if (force) {
            albumApi.popularPage(MovieConstants.API_KEY, params ?: emptyMap())
                    .with(schedulerProvider)
                    .flatMap {
                        val items = it.toListObject()
                        items?.let { albums -> albumDao.insertAll(albums) }
                        Single.just(items)
                    }
        } else {
            albumDao.getAll()
        }
    }

    private fun Page<DiscoverResult>?.toListObject(): List<Album>? {
        return this?.results?.map { it.toObject() }
    }

    private fun DiscoverResult.toObject(): Album {
        return Album(
                id = id,
                title = title,
                remark = overview,
                poster = posterPath
        )
    }
}