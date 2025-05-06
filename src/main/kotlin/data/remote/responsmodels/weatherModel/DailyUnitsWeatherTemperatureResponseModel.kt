package data.remote.responsmodels.weatherModel

import com.google.gson.annotations.SerializedName
import domain.models.weatherModels.DailyUnitsWeatherTemperatureModel
import kotlinx.serialization.Serializable


@Serializable
data class DailyUnitsWeatherTemperatureResponseModel(
    @SerializedName("temperature_2m_max")
    val temperatureMax: String,
    @SerializedName("temperature_2m_min")
    val temperatureMin: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("weather_code")
    val weatherCode: String
){
    fun toDailyUnitsWeatherTemperatureModel():DailyUnitsWeatherTemperatureModel =
        DailyUnitsWeatherTemperatureModel(
            temperatureMax = this.temperatureMax,
            temperatureMin = this.temperatureMin,
            time = this.time,
            weatherCode = this.weatherCode
        )
}