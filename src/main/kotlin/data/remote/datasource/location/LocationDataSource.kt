package data.remote.datasource.location

import data.remote.requestmodels.IpAddressRequestModel
import data.remote.responsmodels.locationModel.LocationResponseModel

interface LocationDataSource {
    suspend fun getCurrentLocation(ipAddress: String): LocationResponseModel
}