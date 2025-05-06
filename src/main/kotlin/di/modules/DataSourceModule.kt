package di.modules

import data.remote.datasource.clothes.ClothesDataSource
import data.remote.datasource.clothes.ClothesRemoteDataSource
import data.remote.datasource.location.LocationDataSource
import data.remote.datasource.location.LocationRemoteDataSource
import data.remote.datasource.weather.WeatherDataSource
import data.remote.datasource.weather.WeatherRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module{

    single<ClothesDataSource>{ ClothesRemoteDataSource() }
    single<LocationDataSource> { LocationRemoteDataSource() }
    single<WeatherDataSource>{ WeatherRemoteDataSource() }

}