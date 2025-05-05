package data.remote.responsmodels.weatherModel

import com.google.gson.annotations.SerializedName

data class DailyResponseWeatherTemperatureModel(
    @SerializedName("temperature_2m_max")
    val temperatureMax: List<Double>,
    @SerializedName("temperature_2m_min")
    val temperatureMin: List<Double>,
    @SerializedName("time")
    val time: List<String>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>
)