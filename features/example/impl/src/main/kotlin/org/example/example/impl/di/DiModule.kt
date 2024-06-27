package org.example.example.impl.di

import com.example.mapper.Mapper
import org.example.example.api.interactors.ExampleInteractor
import org.example.example.api.models.Example
import org.example.example.api.repositories.ExampleRepo
import org.example.example.impl.interactors.ExampleInteractorImpl
import org.example.example.impl.mappers.ExampleMapper
import org.example.example.impl.network.ExampleApi
import org.example.example.impl.network.models.ExampleDto
import org.example.example.impl.paging.ExamplePageSourceFactory
import org.example.example.impl.repositories.ExampleRepoImpl
import org.example.example.impl.screens.screen1.mvi.Screen1Store
import org.example.example.impl.usecases.ExampleUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val exampleDiModule = module {

    factory<Mapper<Example, ExampleDto>> { ExampleMapper() }

    singleOf(::ExampleRepoImpl) bind ExampleRepo::class
    singleOf(::ExampleApi)

    factoryOf(::ExampleInteractorImpl) bind ExampleInteractor::class
    factoryOf(::ExampleUseCase)
    factoryOf(::ExamplePageSourceFactory)

    viewModelOf(::Screen1Store)
}
