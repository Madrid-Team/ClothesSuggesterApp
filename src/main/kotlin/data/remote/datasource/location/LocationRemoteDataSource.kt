package data.remote.datasource.location

import data.networking.safeCall
import data.remote.dtos.locationDto.IpAddressDto
import data.remote.dtos.locationDto.LocationDto
import data.utils.Constants.IP_ADDRESS_BASE_URL
import data.utils.Constants.LOCATION_ADDRESS_BASE_URL
import io.ktor.client.*
import io.ktor.client.request.*

class LocationRemoteDataSource(
    private val httpClient: HttpClient
) : LocationDataSource {

    override suspend fun getCurrentLocation(
        ipAddress: String
    ): LocationDto =
        safeCall<LocationDto> { httpClient.get(LOCATION_ADDRESS_BASE_URL.format(ipAddress)) }

    override suspend fun getIpAddress(): IpAddressDto =
        safeCall<IpAddressDto> { httpClient.get(IP_ADDRESS_BASE_URL) }

}