package data.repositories

import data.remote.datasource.location.LocationRemoteDataSource
import data.utils.toLocationExceptions
import domain.models.location.LocationModel
import domain.repositories.LocationRepository

class LocationRepositoryImpl(
    private val locationRemoteDataSource: LocationRemoteDataSource
) : LocationRepository {
    override suspend fun getCurrentLocation(ipAddress: String): LocationModel {
        try {

            val result = locationRemoteDataSource.getCurrentLocation(ipAddress)
            return result.toLocationModel()
        }catch (exception: Exception) {
            throw exception.toLocationExceptions()
        }
    }
}