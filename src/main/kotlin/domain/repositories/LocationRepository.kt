package domain.repositories

import data.remote.requestmodels.IpAddressRequestModel
import domain.models.location.LocationModel

interface LocationRepository {
    suspend fun getCurrentLocation(ipAddressRequestModel: String): LocationModel
}