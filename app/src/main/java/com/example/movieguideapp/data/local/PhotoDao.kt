package com.example.movieguideapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.movieguideapp.data.model.Photo
import io.reactivex.Single

@Dao
interface PhotoDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(photos: List<Photo>)

    @Query("delete from photo where albumId = :albumId")
    fun deleteByAlbumId(albumId: Int?)

    @Delete
    fun deleteBy(photo:Photo?)

    @Query("select * from photo")
    fun getAll(): List<Photo>

    /**
     * 查询相册下的所有图片
     */
    @Query("select * from photo where albumId = :albumId")
    fun getAllByAlbumId(albumId:Int?): Single<List<Photo>>
}