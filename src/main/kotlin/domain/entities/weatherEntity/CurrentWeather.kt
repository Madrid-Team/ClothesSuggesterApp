package domain.entities.weatherEntity

data class CurrentWeather(
    val cloudCover: Int,
    val interval: Int,
    val isDay: Int,
    val precipitation: Double,
    val rain: Double,
    val relativeHumidity: Int,
    val showers: Double,
    val snowfall: Double,
    val temperature: Double,
    val time: String,
    val weatherCode: Int,
    val windSpeed: Double
)
