package domain.repositories

import domain.models.locationModels.LocationModel

interface LocationRepository {
    suspend fun getCurrentLocation(ipAddress: String): LocationModel


}