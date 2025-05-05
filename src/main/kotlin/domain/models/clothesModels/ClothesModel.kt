package domain.models.clothesModels

data class ClothesModel(
    val female: List<ClothesItemModel>,
    val male: List<ClothesItemModel>
)

data class ClothesItemModel(val description: String,
                       val id: Int,
                       val title: String)