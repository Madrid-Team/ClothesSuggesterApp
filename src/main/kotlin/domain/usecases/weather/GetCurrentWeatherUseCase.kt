package domain.usecases.weather

import domain.models.weatherModels.CurrentWeatherModel
import domain.repositories.WeatherRepository

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun getCurrentWeather(latitude : Double ,longitude: Double)  {

    }


}