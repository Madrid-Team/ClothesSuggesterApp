package data.remote.responsmodels.clothesModel

import kotlinx.serialization.SerialName

data class ClothesItemResponseModel(
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String
)