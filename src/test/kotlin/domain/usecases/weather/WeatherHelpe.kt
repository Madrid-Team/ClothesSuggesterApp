package domain.usecases.weather

import data.remote.dtos.weatherDto.*
import domain.entities.weatherEntity.*


fun createCurrentWeather(
    cloudCover:Int = 0,
    interval:Int = 0,
    isDay:Int = 1,
    precipitation:Double = 0.0,
    rain:Double = 0.0,
    relativeHumidity:Int = 0,
    showers:Double = 0.0,
    snowfall:Double = 0.0,
    temperature:Double = 0.0,
    time:String = "",
    weatherCode:Int = 0,
    windSpeed:Double = 0.0
):CurrentWeather{
    return CurrentWeather(
        cloudCover = 0,
        interval = 0,
        isDay = 1,
        precipitation = 0.0,
        rain = 0.0,
        relativeHumidity = 0,
        showers = 0.0,
        snowfall = 0.0,
        temperature = 0.0,
        time = "",
        weatherCode = 0,
        windSpeed = 0.0
    )
}
fun createWeatherModel(
    current: CurrentWeather = CurrentWeather(
        cloudCover = 0,
        interval = 0,
        isDay = 1,
        precipitation = 0.0,
        rain = 0.0,
        relativeHumidity = 0,
        showers = 0.0,
        snowfall = 0.0,
        temperature = 0.0,
        time = "",
        weatherCode = 0,
        windSpeed = 0.0
    ),
    currentUnits: CurrentUnitsWeather = CurrentUnitsWeather(
        cloudCover = "%",
        interval = "min",
        isDay = "boolean",
        precipitation = "mm",
        rain = "mm",
        relativeHumidity = "%",
        showers = "mm",
        snowfall = "cm",
        temperature = "°C",
        time = "ISO8601",
        weatherCode = "code",
        windSpeed = "km/h"
    ),
    daily: DailyWeather = DailyWeather(
        temperatureMax = listOf(),
        temperatureMin = listOf(),
        time = listOf(),
        weatherCode = listOf()
    ),
    dailyUnits: DailyUnitsWeather = DailyUnitsWeather(
        temperatureMax = "°C",
        temperatureMin = "°C",
        time = "ISO8601",
        weatherCode = "code"
    ),
    elevation: Double = 0.0,
    generationTime: Double = 0.0,
    latitude: Double = 0.0,
    longitude: Double = 0.0,
    timezone: String = "UTC",
    timezoneAbbreviation: String = "UTC",
    utcOffsetSeconds: Int = 0
): Weather {
    return Weather(
        current = current,
        currentUnits = currentUnits,
        daily = daily,
        dailyUnits = dailyUnits,
        elevation = elevation,
        generationTime = generationTime,
        latitude = latitude,
        longitude = longitude,
        timezone = timezone,
        timezoneAbbreviation = timezoneAbbreviation,
        utcOffsetSeconds = utcOffsetSeconds
    )
}


fun createWeatherResponseModel(
    current: CurrentWeatherDto = CurrentWeatherDto(
        cloudCover = 0,
        interval = 0,
        isDay = 1,
        precipitation = 0.0,
        rain = 0.0,
        relativeHumidity = 0,
        showers = 0.0,
        snowfall = 0.0,
        temperature = 0.0,
        time = "",
        weatherCode = 0,
        windSpeed = 0.0
    ),
    currentUnits: CurrentUnitsWeatherDto = CurrentUnitsWeatherDto(
        cloudCover = "%",
        interval = "min",
        isDay = "boolean",
        precipitation = "mm",
        rain = "mm",
        relativeHumidity = "%",
        showers = "mm",
        snowfall = "cm",
        temperature = "°C",
        time = "ISO8601",
        weatherCode = "code",
        windSpeed = "km/h"
    ),
    daily: DailyWeatherTemperatureDto = DailyWeatherTemperatureDto(
        temperatureMax = listOf(),
        temperatureMin = listOf(),
        time = listOf(),
        weatherCode = listOf()
    ),
    dailyUnits: DailyUnitsWeatherTemperatureDto = DailyUnitsWeatherTemperatureDto(
        temperatureMax = "°C",
        temperatureMin = "°C",
        time = "ISO8601",
        weatherCode = "code"
    ),
    elevation: Double = 0.0,
    generationTime: Double = 0.0,
    latitude: Double = 0.0,
    longitude: Double = 0.0,
    timezone: String = "UTC",
    timezoneAbbreviation: String = "UTC",
    utcOffsetSeconds: Int = 0
): WeatherDto {
    return WeatherDto(
        current = current,
        currentUnits = currentUnits,
        daily = daily,
        dailyUnits = dailyUnits,
        elevation = elevation,
        generationTime = generationTime,
        latitude = latitude,
        longitude = longitude,
        timezone = timezone,
        timezoneAbbreviation = timezoneAbbreviation,
        utcOffsetSeconds = utcOffsetSeconds
    )
}