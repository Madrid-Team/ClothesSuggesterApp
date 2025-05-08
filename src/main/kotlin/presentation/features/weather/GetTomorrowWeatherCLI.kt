package presentation.features.weather

import domain.models.weatherModels.CurrentWeatherModel
import domain.models.weatherModels.DailyWeatherTemperatureModel
import domain.usecases.weather.GetTomorrowWeatherUseCase
import domain.utils.exceptions.WeatherException
import presentation.components.InputReader
import presentation.components.OutputPrinter

class GetTomorrowWeatherCLI(
    private val inputReader: InputReader,
    private val outputPrinter: OutputPrinter,
    private val getTomorrowWeatherUseCase: GetTomorrowWeatherUseCase
) {
    suspend fun getTomorrowWeather(latitude: Double, longitude : Double):DailyWeatherTemperatureModel?{
        return try {
            getTomorrowWeatherUseCase.getTomorrowWeather(latitude,longitude)
        } catch (e: WeatherException) {
            outputPrinter.printError("${e.message}")
            null
        }
    }
}