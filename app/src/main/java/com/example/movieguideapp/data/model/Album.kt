package com.example.movieguideapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
        val createDate: String="",
        val ownerId:Int,
)
