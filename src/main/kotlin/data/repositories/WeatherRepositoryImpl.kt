package data.repositories

import data.remote.datasource.weather.WeatherDataSource
import data.utils.toWeatherException
import domain.models.weatherModels.WeatherModel
import domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource
) : WeatherRepository {
    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherModel {
        try {
            val result = weatherDataSource.getWeather(latitude = latitude, longitude = longitude)
            return result.toWeatherModel()
        } catch (exception: Exception) {
            throw exception.toWeatherException()
        }
    }
}