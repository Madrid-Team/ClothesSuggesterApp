package data.remote.dtos.weatherDto

import domain.entities.weatherEntity.Weather
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeatherDto(
    @SerialName("current")
    val current: CurrentWeatherDto,
    @SerialName("current_units")
    val currentUnits: CurrentUnitsWeatherDto,
    @SerialName("daily")
    val daily: DailyWeatherTemperatureDto,
    @SerialName("daily_units")
    val dailyUnits: DailyUnitsWeatherTemperatureDto,
    @SerialName("elevation")
    val elevation: Double,
    @SerialName("generationtime_ms")
    val generationTime: Double,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("timezone")
    val timezone: String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int
){
    fun toWeather(): Weather =
        Weather(
            current = this.current.toCurrentWeather(),
            currentUnits = this.currentUnits.toCurrentUnitsWeather(),
            daily = this.daily.toDailyWeatherTemperature(),
            dailyUnits = this.dailyUnits.toDailyUnitsWeatherTemperature(),
            elevation = this.elevation,
            generationTime = this.generationTime,
            latitude = this.latitude,
            longitude = this.longitude,
            timezone = this.timezone,
            timezoneAbbreviation = this.timezoneAbbreviation,
            utcOffsetSeconds = this.utcOffsetSeconds
        )
}