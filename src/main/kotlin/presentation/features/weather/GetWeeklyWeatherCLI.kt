package presentation.features.weather

import domain.models.weatherModels.CurrentWeatherModel
import domain.models.weatherModels.DailyWeatherTemperatureModel
import domain.usecases.weather.GetWeeklyWeatherUseCase
import domain.utils.exceptions.WeatherException
import presentation.components.InputReader
import presentation.components.OutputPrinter

class GetWeeklyWeatherCLI (
    private val inputReader: InputReader,
    private val outputPrinter: OutputPrinter,
    private val getWeeklyWeatherUseCase: GetWeeklyWeatherUseCase
){
    suspend fun getWeeklyWeather(latitude: Double, longitude : Double): DailyWeatherTemperatureModel {
        return try {
            getWeeklyWeatherUseCase.getWeeklyWeather(latitude,longitude)
        } catch (e: WeatherException) {
            throw e
        }
    }

    fun getTemperatureCategories(weeklyWeather: DailyWeatherTemperatureModel): List<String> {
        return weeklyWeather.temperatureMax.map { temp ->
            when (temp.toInt()) {
                in 0..10 -> "cold.json"
                in 11..20 -> "cool.json"
                in 21..30 -> "warm.json"
                in 31..50 -> "hot.json"
                else -> "unknown.json"
            }
        }
    }

    fun getTemperatureCategory(temp: Double): String {
        return when (temp.toInt()) {
            in 0..10 -> "cold.json"
            in 11..20 -> "cool.json"
            in 21..30 -> "warm.json"
            in 31..50 -> "hot.json"
            else -> "unknown.json"
        }
    }

}