package data.remote.datasource.weather

import data.remote.responsmodels.weatherModel.WeatherResponseModel

class WeatherRemoteDataSource : WeatherDataSource {
    override fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponseModel {
        TODO("Not yet implemented")
    }

    override fun getTomorrowWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponseModel {
        TODO("Not yet implemented")
    }

    override fun getWeeklyWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponseModel {
        TODO("Not yet implemented")
    }
}