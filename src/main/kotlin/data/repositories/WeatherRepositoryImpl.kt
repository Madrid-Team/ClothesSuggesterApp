package data.repositories

import data.remote.datasource.location.LocationDataSource
import data.remote.datasource.weather.WeatherDataSource
import data.utils.toLocationExceptions
import data.utils.toWeatherException
import domain.models.locationModels.IpAddressModel
import domain.models.locationModels.LocationModel
import domain.models.weatherModels.WeatherModel
import domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource,
    private val locationRepository: LocationDataSource

) : WeatherRepository {
    override suspend fun getWeather(
    ): WeatherModel {
        try {
            val newLatitude = getLocation().latitude
            val newLongitude = getLocation().longitude
            val result = weatherDataSource.getWeather(latitude = newLatitude, longitude = newLongitude)
            return result.toWeatherModel()
        } catch (exception: Exception) {
            throw exception.toWeatherException()
        }
    }

    private suspend fun getIpAddress(): IpAddressModel {
        return try {
            locationRepository.getIpAddress().toIpAddressModel()
        }catch (exception: Exception) {
            throw exception.toLocationExceptions()
        }
    }

    private suspend fun getLocation(): LocationModel {
        return try {
            val ipAddress = getIpAddress().ipAddress
            locationRepository.getCurrentLocation(ipAddress).toLocationModel()
        }catch (exception: Exception){
            throw exception.toLocationExceptions()
        }
    }
}