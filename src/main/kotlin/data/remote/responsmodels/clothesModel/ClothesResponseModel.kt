package data.remote.responsmodels.clothesModel

import kotlinx.serialization.SerialName

data class ClothesResponseModel(
    @SerialName("female")
    val femaleClothes: List<ClothesItemResponseModel>,
    @SerialName("male")
    val maleClothes: List<ClothesItemResponseModel>
)