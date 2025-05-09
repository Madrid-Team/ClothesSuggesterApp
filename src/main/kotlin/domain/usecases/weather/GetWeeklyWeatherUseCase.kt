package domain.usecases.weather

import domain.models.weatherModels.DailyWeatherTemperatureModel
import domain.repositories.WeatherRepository
import domain.utils.exceptions.WeatherException

class GetWeeklyWeatherUseCase (
    private val weatherRepository: WeatherRepository
) {

    suspend fun getWeeklyWeather(): DailyWeatherTemperatureModel {
        try {
            val weather = weatherRepository.getWeather()
            return weather.daily
        } catch (e: WeatherException) {
            throw e
        }
    }
}