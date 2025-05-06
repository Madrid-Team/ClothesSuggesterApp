package domain.usecases.weather

import data.remote.requestmodels.IpAddressRequestModel
import domain.models.location.LocationModel
import domain.models.weatherModels.CurrentWeatherModel
import domain.repositories.WeatherRepository
import kotlin.Int

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {

   suspend fun getCurrentWeather(latitude: Double, longitude : Double): CurrentWeatherModel {
        return CurrentWeatherModel(
            cloudCover = 0,
            interval = 0,
            isDay = 0,
            precipitation = 0.0,
            rain = 0.0,
            relativeHumidity = 0,
            showers = 0.0,
            snowfall = 0.0,
            temperature = 0.0,
            time = "",
            weatherCode = 0,
            windSpeed = 0.0
        )


    }

}