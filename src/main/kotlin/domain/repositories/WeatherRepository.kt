package domain.repositories

import domain.models.weatherModels.WeatherModel

interface WeatherRepository {
    suspend fun getWeather(): WeatherModel

}