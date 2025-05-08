package domain.models.locationModels

data class LocationModel(
    val ip: String,
    val city: String,
    val countryName: String,
    val latitude: Double,
    val longitude: Double,
 )
