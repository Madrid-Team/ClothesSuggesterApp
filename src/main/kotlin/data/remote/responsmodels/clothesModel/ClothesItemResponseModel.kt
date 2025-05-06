package data.remote.responsmodels.clothesModel

import domain.models.clothesModels.ClothesItemModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClothesItemResponseModel(
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String
){
    fun toClothesItemModel(): ClothesItemModel =
        ClothesItemModel(
            id = this.id,
            description = this.description,
            title = this.title
        )
}