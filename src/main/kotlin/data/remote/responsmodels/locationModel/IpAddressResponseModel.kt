package data.remote.responsmodels.locationModel

import domain.entities.locationEntity.IpAddress
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IpAddressResponseModel(
    @SerialName("ip")
    val ipAddress: String,
) {
    fun toIpAddressModel(): IpAddress =
        IpAddress(
            ipAddress = this.ipAddress,
        )
}