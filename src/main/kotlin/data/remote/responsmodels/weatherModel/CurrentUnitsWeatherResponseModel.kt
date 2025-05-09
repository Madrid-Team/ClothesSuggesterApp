package data.remote.responsmodels.weatherModel

import domain.entities.weatherEntity.CurrentUnitsWeather
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentUnitsWeatherResponseModel(
    @SerialName("cloud_cover")
    val cloudCover: String,
    @SerialName("interval")
    val interval: String,
    @SerialName("is_day")
    val isDay: String,
    @SerialName("precipitation")
    val precipitation: String,
    @SerialName("rain")
    val rain: String,
    @SerialName("relative_humidity_2m")
    val relativeHumidity: String,
    @SerialName("showers")
    val showers: String,
    @SerialName("snowfall")
    val snowfall: String,
    @SerialName("temperature_2m")
    val temperature: String,
    @SerialName("time")
    val time: String,
    @SerialName("weather_code")
    val weatherCode: String,
    @SerialName("wind_speed_10m")
    val windSpeed: String
) {
    fun toCurrentUnitsWeatherModel(): CurrentUnitsWeather =
        CurrentUnitsWeather(
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