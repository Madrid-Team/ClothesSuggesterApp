package data.repositories

import domain.models.location.LocationModel
import domain.repositories.LocationRepository

class LocationRepositoryImpl : LocationRepository {
    override suspend fun getCurrentLocation(ipAddressRequestModel: String): LocationModel {
        TODO("Not yet implemented")
    }
}