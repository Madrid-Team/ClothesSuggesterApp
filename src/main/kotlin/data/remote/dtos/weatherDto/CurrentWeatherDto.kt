package data.remote.dtos.weatherDto

import domain.entities.weatherEntity.CurrentWeather
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    @SerialName("cloud_cover")
    val cloudCover: Int,
    @SerialName("interval")
    val interval: Int,
    @SerialName("is_day")
    val isDay: Int,
    @SerialName("precipitation")
    val precipitation: Double,
    @SerialName("rain")
    val rain: Double,
    @SerialName("relative_humidity_2m")
    val relativeHumidity: Int,
    @SerialName("showers")
    val showers: Double,
    @SerialName("snowfall")
    val snowfall: Double,
    @SerialName("temperature_2m")
    val temperature: Double,
    @SerialName("time")
    val time: String,
    @SerialName("weather_code")
    val weatherCode: Int,
    @SerialName("wind_speed_10m")
    val windSpeed: Double
) {
    fun toCurrentWeather(): CurrentWeather =
        CurrentWeather(
            cloudCover = this.cloudCover,
            interval = this.interval,
            isDay = this.isDay,
            precipitation = this.precipitation,
            rain = this.rain,
            relativeHumidity = this.relativeHumidity,
            showers = this.showers,
            snowfall = this.snowfall,
            temperature = this.temperature,
            time = this.time,
            weatherCode = this.weatherCode,
            windSpeed = this.windSpeed
        )
}