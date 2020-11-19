package com.example.movieguideapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 相册
 */
@Entity(tableName = "album",)
data class Album(
        @PrimaryKey(autoGenerate = true)
        val id:Int?,
        @ColumnInfo(name = "title")
        val title: String="",
        @ColumnInfo(name = "category")
        val category: String="",
        @ColumnInfo(name = "remark")
        val remark:String="",
        @ColumnInfo(name = "createDate")
        val createDate: String="",
        @ColumnInfo(name = "ownerId")
        val ownerId:Int,
        @ColumnInfo(name = "outId")
        val outId:String?
)
