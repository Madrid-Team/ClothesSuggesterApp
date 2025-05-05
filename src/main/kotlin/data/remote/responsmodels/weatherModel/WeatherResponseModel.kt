package data.remote.responsmodels.weatherModel

import com.google.gson.annotations.SerializedName

data class WeatherResponseModel(
    @SerializedName("current")
    val current: CurrentWeatherResponseModel,
    @SerializedName("current_units")
    val currentUnits: CurrentUnitsWeatherResponseModel,
    @SerializedName("daily")
    val daily: DailyResponseWeatherTemperatureModel,
    @SerializedName("daily_units")
    val dailyUnits: DailyUnitsWeatherTemperatureResponseModel,
    @SerializedName("elevation")
    val elevation: Double,
    @SerializedName("generationtime_ms")
    val generationTime: Double,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)