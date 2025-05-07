package domain.models.weatherModels

data class DailyUnitsWeatherTemperatureModel(
    val temperatureMax: String,
    val temperatureMin: String,
    val time: String,
    val weatherCode: String
)
