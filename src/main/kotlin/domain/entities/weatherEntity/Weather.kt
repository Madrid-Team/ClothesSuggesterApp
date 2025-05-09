package domain.entities.weatherEntity

data class Weather(
    val current: CurrentWeather,
    val currentUnits: CurrentUnitsWeather,
    val daily: DailyWeather,
    val dailyUnits: DailyUnitsWeather,
    val elevation: Double,
    val generationTime: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezoneAbbreviation: String,
    val utcOffsetSeconds: Int
)
