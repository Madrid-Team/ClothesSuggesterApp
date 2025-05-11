package domain.utils

import domain.entities.weatherEntity.DailyWeather

fun getTemperatureCategories(weeklyWeather: DailyWeather): List<String> {
    return weeklyWeather.temperatureMax.map { temp ->
        temp.convertTemperatureToWeatherCode()
    }
}

fun getTemperatureCategory(temp: Double): String {
    return temp.convertTemperatureToWeatherCode()
}

fun Double.convertTemperatureToWeatherCode(): String {
    return when (this.toInt()) {
        in 0..10 -> "cold.json"
        in 11..20 -> "cool.json"
        in 21..30 -> "warm.json"
        in 31..40 -> "hot.json"
        in 41..55 -> "very hot.json"
        else -> "unknown.json"
    }
}