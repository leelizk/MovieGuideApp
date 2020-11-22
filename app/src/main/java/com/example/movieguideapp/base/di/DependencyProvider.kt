package com.example.movieguideapp.base.di

import android.app.Application
import com.example.movieguideapp.ui.viewmodel.BaseActivityViewModel
import com.example.movieguideapp.base.common.schedulers.BaseSchedulerProvider
import com.example.movieguideapp.base.common.schedulers.SchedulerProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class DependencyProvider {

    private val rxModule = module {
        single { SchedulerProvider.instance as BaseSchedulerProvider }
    }

    private val baseModule = module {
        viewModel { BaseActivityViewModel(androidApplication()) }
    }

    private val countryModule = module {
      //  single { CountryRepository(get()) }
      //  factory { DecimalFormat("#,###,###") }
      //  viewModel { CountryViewModel(get(), get(), get(), get()) }
    }

    fun getModules(context: Application) = listOf(
        baseModule,
        rxModule,
       // apiModule,
        countryModule
    )
}