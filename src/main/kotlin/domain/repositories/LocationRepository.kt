package domain.repositories

import domain.models.locationModels.IpAddressModel
import domain.models.locationModels.LocationModel

interface LocationRepository {
    suspend fun getCurrentLocation(ipAddress: String): LocationModel
    suspend fun getIpAddress(): IpAddressModel


}