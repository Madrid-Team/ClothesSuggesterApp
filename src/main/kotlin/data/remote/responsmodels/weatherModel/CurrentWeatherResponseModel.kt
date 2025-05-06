package data.remote.responsmodels.weatherModel

import com.google.gson.annotations.SerializedName
import domain.models.weatherModels.CurrentWeatherModel
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponseModel(
    @SerializedName("cloud_cover")
    val cloudCover: Int,
    @SerializedName("interval")
    val interval: Int,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("precipitation")
    val precipitation: Double,
    @SerializedName("rain")
    val rain: Double,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity: Int,
    @SerializedName("showers")
    val showers: Double,
    @SerializedName("snowfall")
    val snowfall: Double,
    @SerializedName("temperature_2m")
    val temperature: Double,
    @SerializedName("time")
    val time: String,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("wind_speed_10m")
    val windSpeed: Double
) {
    fun toCurrentWeatherModel(): CurrentWeatherModel =
        CurrentWeatherModel(
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