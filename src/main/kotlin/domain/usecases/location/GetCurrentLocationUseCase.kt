package domain.usecases.location

import data.remote.requestmodels.IpAddressRequestModel
import domain.models.location.LocationModel
import domain.repositories.LocationRepository

class GetCurrentLocationUseCase(private val locationRepository: LocationRepository) {

    fun getCurrentLocation(ipAddressRequestModel: String): LocationModel {
        return LocationModel(
            ip = "",
            city = "",
            countryName = "",
            latitude = 0.0,
            longitude = 0.0,
            region = "",
        )
    }
}