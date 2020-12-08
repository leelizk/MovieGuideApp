package com.example.movieguideapp.base


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieguideapp.data.local.AlbumDao
import com.example.movieguideapp.data.local.PhotoDao
import com.example.movieguideapp.data.model.Album
import com.example.movieguideapp.data.model.Photo


@Database(entities = arrayOf(Photo::class, Album::class), version = 2,exportSchema = false) // 定义版本号，便于升级管理
abstract class AppDatabase : RoomDatabase() {
    // 获取DAO
    abstract fun albumDao(): AlbumDao
    abstract fun photoDao(): PhotoDao
}
