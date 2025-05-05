package data.remote.datasource.weather

import data.remote.responsmodels.weatherModel.WeatherResponseModel


interface WeatherDataSource {
    fun getCurrentWeather(latitude: Double, longitude: Double): WeatherResponseModel
    fun getTomorrowWeather(latitude: Double, longitude: Double): WeatherResponseModel
    fun getWeeklyWeather(latitude: Double, longitude: Double): WeatherResponseModel
}