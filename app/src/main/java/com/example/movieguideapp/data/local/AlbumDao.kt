package com.example.movieguideapp.data.local

import androidx.room.*
import com.example.movieguideapp.data.model.Album

//本地数据库接口
@Dao
interface AlbumDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albums: List<Album>)

    @Delete
    fun deleteBy(album:Album?)

    @Query("Delete from album")
    fun deleteAll(albumId:Int?);

    @Query("select * from album")
    fun getAll(): List<Album>

}