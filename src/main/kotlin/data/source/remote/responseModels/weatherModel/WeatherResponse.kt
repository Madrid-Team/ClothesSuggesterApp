package data.source.remote.responseModels

import data.source.remote.responseModels.weatherModel.Current

data class WeatherResponse(
    val current: Current,
    val currentUnits: CurrentUnits,
    val daily: Daily,
    val dailyUnits: DailyUnits,
    val elevation: Double,
    val generationTime: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezoneAbbreviation: String,
    val utcOffsetSeconds: Int
)