package data.utils

import domain.models.weatherModels.DailyWeatherTemperatureModel

fun getTemperatureCategories(weeklyWeather: DailyWeatherTemperatureModel): List<String> {
    return weeklyWeather.temperatureMax.map { temp ->
        when (temp.toInt()) {
            in 0..10 -> "cold.json"
            in 11..20 -> "cool.json"
            in 21..30 -> "warm.json"
            in 31..50 -> "hot.json"
            else -> "unknown.json"
        }
    }
}

fun getTemperatureCategory(temp: Double): String {
    return when (temp.toInt()) {
        in 0..10 -> "cold.json"
        in 11..20 -> "cool.json"
        in 21..30 -> "warm.json"
        in 31..50 -> "hot.json"
        else -> "unknown.json"
    }
}