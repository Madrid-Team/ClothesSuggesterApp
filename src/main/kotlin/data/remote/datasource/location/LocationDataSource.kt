package data.remote.datasource.location

import data.remote.responsmodels.locationModel.LocationResponseModel

interface LocationDataSource {
    fun getCurrentLocation(): LocationResponseModel
}