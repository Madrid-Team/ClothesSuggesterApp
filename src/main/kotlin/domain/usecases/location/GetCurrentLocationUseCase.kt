package domain.usecases.location

import data.remote.requestmodels.IpAddressRequestModel
import domain.models.location.LocationModel
import domain.repositories.LocationRepository
import domain.utils.exceptions.LocationExceptions

class GetCurrentLocationUseCase(private val locationRepository: LocationRepository) {

    suspend fun getCurrentLocation(ipAddressRequestModel: String): LocationModel {
       try {
           return locationRepository.getCurrentLocation(ipAddressRequestModel)
       } catch (e: LocationExceptions){
           throw e
       }

    }
}