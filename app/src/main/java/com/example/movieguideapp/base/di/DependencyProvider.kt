package com.example.movieguideapp.base.di

import android.app.Application
import com.example.movieguideapp.base.AppDatabaseProvider
import com.example.movieguideapp.ui.viewmodel.BaseActivityViewModel
import com.example.movieguideapp.base.common.schedulers.BaseSchedulerProvider
import com.example.movieguideapp.base.common.schedulers.SchedulerProvider
import com.example.movieguideapp.data.local.CountryRepository
import com.example.movieguideapp.base.di.ApiModule.Companion.apiModule
import com.example.movieguideapp.data.local.impl.AlbumDaoImpl
import com.example.movieguideapp.ui.viewmodel.AlbumListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 *
 * 使用koin 自动注入
 */

class DependencyProvider {

    private val rxModule = module {
        single { SchedulerProvider.instance as BaseSchedulerProvider }
    }

    private val baseModule = module {
        single { CountryRepository(get()) }
        viewModel { BaseActivityViewModel(androidApplication()) }
    }

    private val albumModule = module {
        single { AlbumDaoImpl(get(),get(),get()) }
    }

    private val daoModule = module{
        single{AppDatabaseProvider(androidApplication()).provideAppDataBase().albumDao()}
        single{AppDatabaseProvider(androidApplication()).provideAppDataBase().photoDao()}
    }


    private val albumListViewModel = module{
        //定义新增的viewModel
        viewModel { AlbumListViewModel(androidApplication(),get(),get(),get()) }
    }

    /*private val countryModule = module {
        single { CountryRepository(get()) }
        factory { DecimalFormat("#,###,###") }
       //viewModel { CountryViewModel(get(), get(), get(), get()) }
    }*/

    fun getModules(context: Application) = listOf(
        baseModule,
        rxModule,
        apiModule,
        daoModule,
        albumModule,
        albumListViewModel,
    )
}