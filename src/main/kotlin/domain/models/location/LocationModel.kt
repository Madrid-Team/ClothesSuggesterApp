package domain.models.location

data class LocationModel(
    val ip: String,
    val city: String,
    val countryName: String,
    val latitude: Double,
    val longitude: Double,
    val region: String,
)
