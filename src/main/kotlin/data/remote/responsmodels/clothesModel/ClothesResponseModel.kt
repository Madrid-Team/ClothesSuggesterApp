package data.remote.responsmodels.clothesModel

import domain.models.clothesModels.ClothesModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClothesResponseModel(
    @SerialName("female")
    val femaleClothes: List<ClothesItemResponseModel>,
    @SerialName("male")
    val maleClothes: List<ClothesItemResponseModel>
) {
    fun toClothesModel(): ClothesModel =
        ClothesModel(
            femaleClothes = this.femaleClothes.map { it.toClothesItemModel() },
            maleClothes = this.maleClothes.map { it.toClothesItemModel() }
        )
}