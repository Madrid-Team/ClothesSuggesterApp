package data.remote.responsmodels.weatherModel

import com.google.gson.annotations.SerializedName

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
)