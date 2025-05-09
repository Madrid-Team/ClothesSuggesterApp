package presentation.features.weather

import domain.models.weatherModels.CurrentWeatherModel
import domain.usecases.weather.GetCurrentWeatherUseCase
import domain.utils.exceptions.WeatherException
import presentation.components.InputReader
import presentation.components.OutputPrinter

class GetCurrentWeatherCLI(
    private val inputReader: InputReader,
    private val outputPrinter: OutputPrinter,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) {
    suspend fun getCurrentWeather( ): CurrentWeatherModel  {
        return try {
            getCurrentWeatherUseCase.getCurrentWeather( )
        } catch (e: WeatherException) {
          throw e
        }
    }
     fun getTemperatureCategory(weatherModel: CurrentWeatherModel?): String {
        val temperature = weatherModel?.temperature ?: return "unknown.json"

        return when (temperature.toInt()) {
            in 0..10 -> "cold.json"
            in 11..20 -> "cool.json"
            in 21..30 -> "warm.json"
            in 31..50 -> "hot.json"
            else -> "unknown.json"
        }
    }

}