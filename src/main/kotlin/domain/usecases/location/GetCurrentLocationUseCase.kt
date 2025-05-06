package domain.usecases.location

import domain.models.location.LocationModel
import domain.repositories.LocationRepository
import domain.utils.exceptions.LocationException

class GetCurrentLocationUseCase(private val locationRepository: LocationRepository) {

    suspend fun getCurrentLocation(ipAddressRequestModel: String): LocationModel {
       try {
           return locationRepository.getCurrentLocation(ipAddressRequestModel)
       } catch (e: LocationException){
           throw e
       }

    }
}