package com.example.movieguideapp.base

import android.content.Context
import androidx.room.Room

class AppDatabaseProvider(
	private val context: Context
) {

	companion object {
		private const val DB_NAME = "mytest1.db"
	}

	fun provideAppDataBase(): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
			.build()
}
