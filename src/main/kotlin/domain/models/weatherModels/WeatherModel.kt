package domain.models.weatherModels

data class WeatherModel(
    val current: CurrentWeatherModel,
    val currentUnits: CurrentUnitsWeatherModel,
    val daily: DailyWeatherTemperatureModel,
    val dailyUnits: DailyUnitsWeatherTemperatureModel,
    val elevation: Double,
    val generationTime: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezoneAbbreviation: String,
    val utcOffsetSeconds: Int
)
