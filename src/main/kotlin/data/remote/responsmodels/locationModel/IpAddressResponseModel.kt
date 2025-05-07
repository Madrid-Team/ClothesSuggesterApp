package data.remote.responsmodels.locationModel

import domain.models.locationModels.IpAddressModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IpAddressResponseModel(
    @SerialName("ip")
    val ipAddress: String,
) {
    fun toIpAddressModel(): IpAddressModel =
        IpAddressModel(
            ipAddress = this.ipAddress,
        )
}