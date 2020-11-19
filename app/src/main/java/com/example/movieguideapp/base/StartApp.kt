package com.example.movieguideapp.base

import android.app.Application
import androidx.room.Room

class StartApp : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    //初始化
    fun init(){
        //初始化数据库
        AppDatabaseProvider(this).provideAppDataBase()
    }


}