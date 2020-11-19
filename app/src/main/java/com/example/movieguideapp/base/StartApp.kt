package com.example.movieguideapp.base

import android.app.Application
import android.util.Log

class StartApp : Application() {
    private val TAG:String = StartApp::class.java.simpleName;
    override fun onCreate() {
        super.onCreate()
        init()
    }

    //初始化
    fun init(){
        Log.d(TAG,"init...")
        //初始化数据库 or something else here
        AppDatabaseProvider(this).provideAppDataBase()
    }


}