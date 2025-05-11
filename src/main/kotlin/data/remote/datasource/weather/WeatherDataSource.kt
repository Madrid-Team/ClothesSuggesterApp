package data.remote.datasource.weather

import data.remote.dtos.weatherDto.WeatherDto


interface WeatherDataSource {
    suspend fun getWeather(latitude: Double, longitude: Double): WeatherDto
}