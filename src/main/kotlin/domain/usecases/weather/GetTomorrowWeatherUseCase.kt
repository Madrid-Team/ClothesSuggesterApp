package domain.usecases.weather

import domain.models.weatherModels.CurrentWeatherModel
import domain.models.weatherModels.DailyWeatherTemperatureModel
import domain.repositories.WeatherRepository
import kotlin.Double
import kotlin.collections.List

class GetTomorrowWeatherUseCase(private val weatherRepository: WeatherRepository) {

    fun getTomorrowWeatherUseCase(latitude: Double, longitude: Double): DailyWeatherTemperatureModel {
        return DailyWeatherTemperatureModel(
            temperatureMax = listOf(),
            temperatureMin = listOf(),
            time = listOf(),
            weatherCode = listOf()
        )


    }


}
