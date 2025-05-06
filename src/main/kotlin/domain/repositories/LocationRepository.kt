package domain.repositories

import domain.models.location.LocationModel

interface LocationRepository {
    suspend fun getCurrentLocation(ipAddress: String): LocationModel

}