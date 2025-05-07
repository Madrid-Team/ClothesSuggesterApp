package domain.usecases.location

import domain.models.locationModels.IpAddressModel
import domain.repositories.LocationRepository
import domain.utils.exceptions.LocationException

class GetIpAddressUseCase(private val locationRepository: LocationRepository) {
    suspend fun getIpAddress(): IpAddressModel {
        try {
            return locationRepository.getIpAddress()
        } catch (e: LocationException) {
            throw e
        }
    }
}