package data.remote.datasource.location

import data.remote.requestmodels.IpAddressRequestModel
import data.remote.responsmodels.locationModel.LocationResponseModel

class LocationRemoteDataSource: LocationDataSource {
    override suspend fun getCurrentLocation(ipAddress: IpAddressRequestModel): LocationResponseModel {
        TODO("Not yet implemented")
    }

}