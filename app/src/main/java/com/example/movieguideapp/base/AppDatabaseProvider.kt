package com.example.movieguideapp.base

import android.content.Context
import androidx.room.Room

class AppDatabaseProvider(
	private val context: Context
) {

	//定义数据库文件名称
	companion object {
		private const val DB_NAME = "mytest1.db"
	}

	//获取数据库引用
	fun provideAppDataBase(): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
			.build()
}
