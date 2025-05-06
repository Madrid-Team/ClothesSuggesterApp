package data.repositories

import data.remote.datasource.weather.WeatherRemoteDataSource
import data.utils.toWeatherException
import domain.models.weatherModels.WeatherModel
import domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {
    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherModel {
        try {
            val result = weatherRemoteDataSource.getWeather(latitude = latitude, longitude = longitude)
            return result.toWeatherModel()
        } catch (exception: Exception) {
            throw exception.toWeatherException()
        }
    }
}