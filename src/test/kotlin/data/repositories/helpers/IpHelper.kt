package data.repositories.helpers

import data.remote.dtos.locationDto.IpAddressDto

fun createIpResponseModel(ipAddress : String) : IpAddressDto = IpAddressDto(ipAddress)