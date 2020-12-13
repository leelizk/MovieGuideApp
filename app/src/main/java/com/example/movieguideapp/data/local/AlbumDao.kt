package com.example.movieguideapp.data.local

import androidx.room.*
import com.example.movieguideapp.data.model.Album
import com.example.movieguideapp.data.remote.AlbumApi
import io.reactivex.Single

//本地数据库接口
@Dao
interface AlbumDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albums: List<Album>)

    @Delete
    fun deleteBy(album:Album?)

    @Query("delete from album")
    fun deleteAll()

    @Query("select * from album")
    fun getAll(): Single<List<Album>>

    @Query("select count(1) from album")
    fun getAllCount():Int

}