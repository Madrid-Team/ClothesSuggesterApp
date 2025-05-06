package domain.usecases.weather

import domain.models.weatherModels.CurrentWeatherModel
import domain.models.weatherModels.DailyWeatherTemperatureModel
import domain.repositories.WeatherRepository
import domain.utils.exceptions.WeatherExceptions
import kotlin.Double
import kotlin.collections.List

class GetTomorrowWeatherUseCase(private val weatherRepository: WeatherRepository) {

   suspend fun getTomorrowWeatherUseCase(latitude: Double, longitude: Double): DailyWeatherTemperatureModel {
       try {
           return weatherRepository.getWeather(latitude,longitude).daily
       }catch (e: WeatherExceptions){
           throw e
       }

    }


}
