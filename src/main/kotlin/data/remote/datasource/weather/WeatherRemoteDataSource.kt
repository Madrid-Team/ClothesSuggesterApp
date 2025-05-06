package data.remote.datasource.weather

import data.networking.safeCall
import data.remote.responsmodels.weatherModel.WeatherResponseModel
import data.utils.Constants.WEATHER_BASE_URL
import data.utils.Constants.WEATHER_CURRENT_PARAMETER
import data.utils.Constants.WEATHER_DAILY_PARAMETER
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class WeatherRemoteDataSource(
    private val httpClient: HttpClient
) : WeatherDataSource {
    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponseModel =
        safeCall<WeatherResponseModel> {
            httpClient.get{
                url(WEATHER_BASE_URL)
                parameter("latitude", latitude)
                parameter("longitude", longitude)
                parameter("daily", WEATHER_DAILY_PARAMETER)
                parameter("current", WEATHER_CURRENT_PARAMETER)
            }
        }

}