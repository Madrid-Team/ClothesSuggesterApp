package domain.entities.weatherEntity

data class CurrentUnitsWeather(
    val cloudCover: String,
    val interval: String,
    val isDay: String,
    val precipitation: String,
    val rain: String,
    val relativeHumidity: String,
    val showers: String,
    val snowfall: String,
    val temperature: String,
    val time: String,
    val weatherCode: String,
    val windSpeed: String
)
