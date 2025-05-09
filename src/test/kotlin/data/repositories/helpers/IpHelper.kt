package data.repositories.helpers

import data.remote.responsmodels.locationModel.IpAddressResponseModel

fun createIpResponseModel(ipAddress : String) : IpAddressResponseModel = IpAddressResponseModel(ipAddress)