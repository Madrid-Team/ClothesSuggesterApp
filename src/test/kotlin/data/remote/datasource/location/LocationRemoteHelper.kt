package data.remote.datasource.location

import data.remote.responsmodels.locationModel.LocationResponseModel

fun createLocationRemoteModel(ipAddress: String): LocationResponseModel {

    return LocationResponseModel(
        asn = "",
        city = "",
        countryCode = "",
        country = "",
        countryArea = 0.0,
        countryCapital = "",
        countryCallingCode = "",
        continentCode = "",
        countryCodeIso3 = "",
        countryName = "",
        countryPopulation = 0,
        countryTld = "",
        currency = "",
        currencyName = "",
        inEu = true,
        ip = ipAddress,
        languages = "",
        latitude = 0.0,
        longitude = 0.0,
        network = "",
        org = "",
        region = "",
        regionCode = "",
        timezone = "",
        utcOffset = "",
        version = ""
    )
}

