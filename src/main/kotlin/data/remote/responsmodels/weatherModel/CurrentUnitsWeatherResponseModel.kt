package data.remote.responsmodels.weatherModel

import com.google.gson.annotations.SerializedName

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
)