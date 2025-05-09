package domain.repositories

import domain.entities.weatherEntity.Weather

interface WeatherRepository {
    suspend fun getWeather(): Weather

}