package data.remote.datasource.weather

import data.remote.responsmodels.weatherModel.WeatherResponseModel

class WeatherRemoteDataSource : WeatherDataSource {
    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponseModel {
        TODO("Not yet implemented")
    }

}