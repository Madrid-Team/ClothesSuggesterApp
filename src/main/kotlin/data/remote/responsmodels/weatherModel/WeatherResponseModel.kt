package data.remote.responsmodels.weatherModel

import com.google.gson.annotations.SerializedName
import domain.models.weatherModels.WeatherModel
import kotlinx.serialization.Serializable


@Serializable
data class WeatherResponseModel(
    @SerializedName("current")
    val current: CurrentWeatherResponseModel,
    @SerializedName("current_units")
    val currentUnits: CurrentUnitsWeatherResponseModel,
    @SerializedName("daily")
    val daily: DailyWeatherTemperatureResponseModel,
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
){
    fun toWeatherModel(): WeatherModel =
        WeatherModel(
            current = this.current.toCurrentWeatherModel(),
            currentUnits = this.currentUnits.toCurrentUnitsWeatherModel(),
            daily = this.daily.toDailyWeatherTemperatureModel(),
            dailyUnits = this.dailyUnits.toDailyUnitsWeatherTemperatureModel(),
            elevation = this.elevation,
            generationTime = this.generationTime,
            latitude = this.latitude,
            longitude = this.longitude,
            timezone = this.timezone,
            timezoneAbbreviation = this.timezoneAbbreviation,
            utcOffsetSeconds = this.utcOffsetSeconds
        )
}