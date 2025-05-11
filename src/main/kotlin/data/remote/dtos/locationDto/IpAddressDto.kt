package data.remote.dtos.locationDto

import domain.entities.locationEntity.IpAddress
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IpAddressDto(
    @SerialName("ip")
    val ipAddress: String,
) {
    fun toIpAddress(): IpAddress =
        IpAddress(
            ipAddress = this.ipAddress,
        )
}