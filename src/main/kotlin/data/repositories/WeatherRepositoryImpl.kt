package data.repositories

import domain.models.weatherModels.WeatherModel
import domain.repositories.WeatherRepository

class WeatherRepositoryImpl  : WeatherRepository{
    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherModel {
        TODO("Not yet implemented")
    }
}