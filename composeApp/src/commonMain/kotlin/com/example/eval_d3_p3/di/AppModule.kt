package com.example.eval_d3_p3.di

import com.example.eval_d3_p3.data.local.InMemoryLocationCache
import com.example.eval_d3_p3.data.remote.LocationApi
import com.example.eval_d3_p3.data.repository.LocationRepositoryImpl
import com.example.eval_d3_p3.domain.repository.LocationRepository
import com.example.eval_d3_p3.domain.usecase.GetLocationDetailUseCase
import com.example.eval_d3_p3.domain.usecase.GetLocationsUseCase
import com.example.eval_d3_p3.network.createHttpClient
import com.example.eval_d3_p3.platform.PlatformContext
import com.example.eval_d3_p3.platform.SoundManager
import com.example.eval_d3_p3.presentation.locationdetail.LocationDetailViewModel
import com.example.eval_d3_p3.presentation.locationlist.LocationListViewModel
import org.koin.dsl.module

val appModule = module {
    // Central DI wiring for data, domain, and presentation layers.
    single { createHttpClient() }
    single { LocationApi(get()) }
    single { InMemoryLocationCache() }
    single<LocationRepository> { LocationRepositoryImpl(get(), get()) }
    factory { GetLocationsUseCase(get()) }
    factory { GetLocationDetailUseCase(get()) }
    factory { LocationListViewModel(get()) }
    factory { LocationDetailViewModel(get()) }
    factory { (platformContext: PlatformContext) -> SoundManager(platformContext) }
}
