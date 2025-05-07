package domain.models.weatherModels

data class DailyWeatherTemperatureModel(
    val temperatureMax: List<Double>,
    val temperatureMin: List<Double>,
    val time: List<String>,
    val weatherCode: List<Int>
)
