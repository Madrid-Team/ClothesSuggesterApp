package data.repositories

import data.remote.datasource.location.LocationDataSource
import data.remote.datasource.weather.WeatherDataSource
import data.utils.toLocationExceptions
import data.utils.toWeatherException
import domain.entities.locationEntity.IpAddress
import domain.entities.locationEntity.Location
import domain.entities.weatherEntity.Weather
import domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource,
    private val locationRepository: LocationDataSource

) : WeatherRepository {
    override suspend fun getWeather(
    ): Weather {
        try {
            val location = getLocation()
            val result = weatherDataSource.getWeather(latitude = location.latitude, longitude = location.longitude)
            return result.toWeather()
        } catch (exception: Exception) {
            throw exception.toWeatherException()
        }
    }

    internal suspend fun getIpAddress(): IpAddress {
        return try {
            locationRepository.getIpAddress().toIpAddress()
        }catch (exception: Exception) {
            throw exception.toLocationExceptions()
        }
    }

    internal suspend fun getLocation(): Location {
        return try {
            val ipAddress = getIpAddress().ipAddress
            locationRepository.getCurrentLocation(ipAddress).toLocation()
        }catch (exception: Exception){
            throw exception.toLocationExceptions()
        }
    }
}