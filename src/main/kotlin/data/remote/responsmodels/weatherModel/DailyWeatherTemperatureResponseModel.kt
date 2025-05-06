package data.remote.responsmodels.weatherModel

import com.google.gson.annotations.SerializedName
import domain.models.weatherModels.DailyWeatherTemperatureModel
import kotlinx.serialization.Serializable


@Serializable
data class DailyWeatherTemperatureResponseModel(
    @SerializedName("temperature_2m_max")
    val temperatureMax: List<Double>,
    @SerializedName("temperature_2m_min")
    val temperatureMin: List<Double>,
    @SerializedName("time")
    val time: List<String>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>
) {
    fun toDailyWeatherTemperatureModel(): DailyWeatherTemperatureModel =
        DailyWeatherTemperatureModel(
            temperatureMax = this.temperatureMax,
            temperatureMin = this.temperatureMin,
            time = this.time,
            weatherCode = this.weatherCode
        )
}