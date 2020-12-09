package com.example.movieguideapp.base.di

import com.example.movieguideapp.BuildConfig
import com.example.movieguideapp.data.remote.AlbumApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiModule {

    companion object {
        val apiModule = ApiModule().provideModules()
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    private fun okHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.enableLoggingIfNeeded()
        return clientBuilder.build()
    }

    private fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /*private fun provideCountryApiService(retrofit: Retrofit): CountryApiService {
        return retrofit.create(CountryApiService::class.java)
    }*/

    private fun provideAlbumApiService(retrofit: Retrofit):AlbumApi{
        return retrofit.create(AlbumApi::class.java);
    }

    fun provideModules() = module {
        single { Gson() }
        single { okHttpClient() }
        single { retrofit(get()) }
        single { provideAlbumApiService(get()) }
    }

    private fun OkHttpClient.Builder.enableLoggingIfNeeded() {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }
}