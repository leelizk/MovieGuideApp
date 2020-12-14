package com.example.movieguideapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class Photo(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
        @ColumnInfo(name = "albumId")
        val albumId: Long? = null,
        @ColumnInfo(name = "title")
        val title: String? = null,
        @ColumnInfo(name = "url")
        val url: String? = null,
        @ColumnInfo(name = "thumbnailUrl")
        val thumbnailUrl: String? = null
)
