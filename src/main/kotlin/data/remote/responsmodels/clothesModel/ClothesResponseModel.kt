package data.remote.responsmodels.clothesModel

data class ClothesResponseModel(
    val femaleClothes: List<ClothesItemResponseModel>,
    val maleClothes: List<ClothesItemResponseModel>
)