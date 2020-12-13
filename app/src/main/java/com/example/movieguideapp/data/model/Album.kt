package com.example.movieguideapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 相册
 */
@Entity(tableName = "album")
data class Album(
        @PrimaryKey(autoGenerate = true)
        val id:Long? = null,
        @ColumnInfo(name = "title")
        val title: String? = null,
        @ColumnInfo(name = "category")
        val category:String? = null,
        @ColumnInfo(name = "remark")
        val remark:String? = null,
        @ColumnInfo(name = "createDate")
        val createDate: String? = null,
        @ColumnInfo(name = "ownerId")
        val ownerId:Int? = null,
        @ColumnInfo(name = "outId")
        val outId:String? = null,
        @ColumnInfo(name = "poster")
        val poster:String? = null
)
