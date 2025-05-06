package data.repositories

import data.remote.datasource.location.LocationDataSource
import data.utils.toLocationExceptions
import domain.models.location.LocationModel
import domain.repositories.LocationRepository

class LocationRepositoryImpl(
    private val locationRepository: LocationDataSource
) : LocationRepository {
    override suspend fun getCurrentLocation(ipAddress: String): LocationModel {
        try {

            val result = locationRepository.getCurrentLocation(ipAddress)
            return result.toLocationModel()
        }catch (exception: Exception) {
            throw exception.toLocationExceptions()
        }
    }
}