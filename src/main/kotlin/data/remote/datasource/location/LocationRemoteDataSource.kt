package data.remote.datasource.location

import data.networking.safeCall
import data.remote.responsmodels.locationModel.IpAddressResponseModel
import data.remote.responsmodels.locationModel.LocationResponseModel
import data.utils.Constants.IP_ADDRESS_BASE_URL
import data.utils.Constants.LOCATION_ADDRESS_BASE_URL
import io.ktor.client.*
import io.ktor.client.request.*

class LocationRemoteDataSource(
    private val httpClient: HttpClient
) : LocationDataSource {

    override suspend fun getCurrentLocation(
        ipAddress: String
    ): LocationResponseModel =
        safeCall<LocationResponseModel> { httpClient.get(LOCATION_ADDRESS_BASE_URL.format(ipAddress)) }

    override suspend fun getIpAddress(): IpAddressResponseModel =
        safeCall<IpAddressResponseModel> { httpClient.get(IP_ADDRESS_BASE_URL) }

}