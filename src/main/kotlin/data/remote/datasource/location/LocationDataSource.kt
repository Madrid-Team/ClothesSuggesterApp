package data.remote.datasource.location

import data.remote.dtos.locationDto.IpAddressDto
import data.remote.dtos.locationDto.LocationDto

interface LocationDataSource {
    suspend fun getCurrentLocation(ipAddress: String): LocationDto
    suspend fun getIpAddress(): IpAddressDto
}