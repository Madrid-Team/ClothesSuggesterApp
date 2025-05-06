package domain.usecases.location

import data.remote.requestmodels.IpAddressRequestModel
import domain.models.location.LocationModel
import domain.repositories.LocationRepository

class GetCurrentLocationUseCase(private val locationRepository: LocationRepository) {

    suspend fun getCurrentLocation(ipAddressRequestModel: String): LocationModel {
        return locationRepository.getCurrentLocation(ipAddressRequestModel)

    }
}