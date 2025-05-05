package data.remote.datasource.weather

import data.remote.responsmodels.weatherModel.WeatherResponseModel


interface WeatherDataSource {
    suspend fun getWeather(latitude: Double, longitude: Double): WeatherResponseModel
}