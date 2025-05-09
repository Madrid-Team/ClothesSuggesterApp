package di.modules

import data.repositories.ClothesRepositoryImpl
import data.repositories.WeatherRepositoryImpl
import domain.repositories.ClothesRepository
import domain.repositories.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module{

    single<ClothesRepository> { ClothesRepositoryImpl(get()) }
    single <WeatherRepository>{ WeatherRepositoryImpl(get(),get()) }

}