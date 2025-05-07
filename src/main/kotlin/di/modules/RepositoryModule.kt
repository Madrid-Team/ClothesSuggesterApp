package di.modules

import data.repositories.ClothesRepositoryImpl
import data.repositories.LocationRepositoryImpl
import data.repositories.WeatherRepositoryImpl
import domain.repositories.ClothesRepository
import domain.repositories.LocationRepository
import domain.repositories.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module{

    single<ClothesRepository> { ClothesRepositoryImpl(get()) }
    single <LocationRepository>{ LocationRepositoryImpl(get()) }
    single <WeatherRepository>{ WeatherRepositoryImpl(get()) }

}