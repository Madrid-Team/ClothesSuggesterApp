package domain.usecases.weather

import data.remote.requestmodels.IpAddressRequestModel
import domain.models.location.LocationModel
import domain.models.weatherModels.CurrentWeatherModel
import domain.repositories.WeatherRepository
import domain.utils.exceptions.WeatherExceptions
import kotlin.Int

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {

   suspend fun getCurrentWeather(latitude: Double, longitude : Double): CurrentWeatherModel {
       try {
           return weatherRepository.getWeather(latitude,longitude).current
       }catch (e: WeatherExceptions){
           throw e
       }

    }

}