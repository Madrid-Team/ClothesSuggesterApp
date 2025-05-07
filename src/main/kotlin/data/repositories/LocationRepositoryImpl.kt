package data.repositories


import data.remote.datasource.location.LocationDataSource
import data.utils.toLocationExceptions
import domain.models.locationModels.IpAddressModel
import domain.models.locationModels.LocationModel
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

    override suspend fun getIpAddress(): IpAddressModel {
         try {
             val result = locationRepository.getIpAddress()
             return result.toIpAddressModel()
         }catch (exception: Exception) {
             throw exception.toLocationExceptions()
         }
    }
}