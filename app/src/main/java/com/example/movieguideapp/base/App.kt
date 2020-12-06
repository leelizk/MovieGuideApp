package com.example.movieguideapp.base

import android.app.Application
import android.util.Log
import com.example.movieguideapp.base.di.DependencyProvider
import com.example.movieguideapp.data.local.AlbumDao
import com.example.movieguideapp.data.local.PhotoDao
import com.example.movieguideapp.data.model.Album
import com.example.movieguideapp.data.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    private val TAG:String = App::class.java.simpleName;
    lateinit var photoDao: PhotoDao;
    lateinit var albumDao:AlbumDao;

    override fun onCreate() {
        super.onCreate()
        initInjection()
        init()
    }


    private fun initInjection() {
        val dependencyProvider = DependencyProvider()
        startKoin {
            androidContext(this@App)
            modules(dependencyProvider.getModules(this@App))
        }
    }


    //初始化
    fun init(){
        Log.d(TAG,"init...fake data")
        //初始化数据库 or something else here
        albumDao = AppDatabaseProvider(this).provideAppDataBase().albumDao();
        photoDao = AppDatabaseProvider(this).provideAppDataBase().photoDao();
        GlobalScope.launch(Dispatchers.IO) {
            var count:Int = albumDao.getAllCount();

            if(count<= 0){

                var albumList:MutableList<Album> = mutableListOf();

                var album:Album = Album(1,"相册"+1,"我的","试试","2020-11-20",1,"lizk");
                albumList.add(album);

                GlobalScope.launch {
                    albumDao.insertAll(albumList);
                }

                var photoList:MutableList<Photo> = mutableListOf();
                for(i in 1..11){
                    var photo:Photo = Photo(1,1,"测试1","","");
                    photoList.add(photo);
                }
                GlobalScope.launch {
                    photoDao.insertAll(photoList);
                }

            }else{
                Log.i(TAG,"已有缓存")
            }
        }

    }


}