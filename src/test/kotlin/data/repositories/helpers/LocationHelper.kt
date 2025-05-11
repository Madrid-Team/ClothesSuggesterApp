package data.repositories.helpers

import data.remote.dtos.locationDto.LocationDto



fun createLocationResponseModel(
    asn: String = "AS1234",
    city: String = "Cairo",
    continentCode: String = "AF",
    country: String = "Egypt",
    countryArea: Double = 1000.0,
    countryCallingCode: String = "+20",
    countryCapital: String = "Cairo",
    countryCode: String = "EG",
    countryCodeIso3: String = "EGY",
    countryName: String = "Egypt",
    countryPopulation: Int = 100000000,
    countryTld: String = ".eg",
    currency: String = "EGP",
    currencyName: String = "Egyptian Pound",
    inEu: Boolean = false,
    ip: String = "192.168.1.1",
    languages: String = "ar",
    latitude: Double = 30.0444,
    longitude: Double = 31.2357,
    network: String = "Network",
    org: String = "Org",
    regionCode: String = "C",
    timezone: String = "Africa/Cairo",
    utcOffset: String = "+0200",
    version: String = "IPv4"
): LocationDto {
    return LocationDto(
        asn = asn,
        city = city,
        continentCode = continentCode,
        country = country,
        countryArea = countryArea,
        countryCallingCode = countryCallingCode,
        countryCapital = countryCapital,
        countryCode = countryCode,
        countryCodeIso3 = countryCodeIso3,
        countryName = countryName,
        countryPopulation = countryPopulation,
        countryTld = countryTld,
        currency = currency,
        currencyName = currencyName,
        inEu = inEu,
        ip = ip,
        languages = languages,
        latitude = latitude,
        longitude = longitude,
        network = network,
        org = org,
        regionCode = regionCode,
        timezone = timezone,
        utcOffset = utcOffset,
        version = version
    )
}
