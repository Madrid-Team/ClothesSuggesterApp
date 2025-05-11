package domain.usecases.weather

import domain.entities.weatherEntity.DailyWeather
import domain.repositories.WeatherRepository
import domain.utils.exceptions.WeatherException

class GetWeeklyWeatherUseCase (
    private val weatherRepository: WeatherRepository
) {

    suspend fun getWeeklyWeather(): DailyWeather {
        try {
            val dailyWeather = weatherRepository.getWeather().daily
            return dailyWeather
        } catch (e: WeatherException) {
            throw e
        }
    }
}