package domain.usecases.weather

import domain.repositories.WeatherRepository

class GetTomorrowWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun getTomorrowWeather(latitude : Double, longitude : Double){

    }

}