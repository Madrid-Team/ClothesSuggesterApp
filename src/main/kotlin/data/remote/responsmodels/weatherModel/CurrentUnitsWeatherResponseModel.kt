package data.remote.responsmodels.weatherModel

import com.google.gson.annotations.SerializedName
import domain.models.weatherModels.CurrentUnitsWeatherModel
import kotlinx.serialization.Serializable

@Serializable
data class CurrentUnitsWeatherResponseModel(
    @SerializedName("cloud_cover")
    val cloudCover: String,
    @SerializedName("interval")
    val interval: String,
    @SerializedName("is_day")
    val isDay: String,
    @SerializedName("precipitation")
    val precipitation: String,
    @SerializedName("rain")
    val rain: String,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity: String,
    @SerializedName("showers")
    val showers: String,
    @SerializedName("snowfall")
    val snowfall: String,
    @SerializedName("temperature_2m")
    val temperature: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("weather_code")
    val weatherCode: String,
    @SerializedName("wind_speed_10m")
    val windSpeed: String
) {
    fun toCurrentUnitsWeatherModel(): CurrentUnitsWeatherModel =
        CurrentUnitsWeatherModel(
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