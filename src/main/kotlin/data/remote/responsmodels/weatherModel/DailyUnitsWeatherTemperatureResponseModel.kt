package data.remote.responsmodels.weatherModel

import com.google.gson.annotations.SerializedName

data class DailyUnitsWeatherTemperatureResponseModel(
    @SerializedName("temperature_2m_max")
    val temperatureMax: String,
    @SerializedName("temperature_2m_min")
    val temperatureMin: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("weather_code")
    val weatherCode: String
)