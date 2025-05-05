package data.remote.responsmodels.clothesModel

data class ClothesResponseModel(
    val female: List<ClothesItemResponseModel>,
    val male: List<ClothesItemResponseModel>
)