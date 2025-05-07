package domain.models.clothesModels

data class ClothesModel(
    val femaleClothes: List<ClothesItemModel>,
    val maleClothes: List<ClothesItemModel>
){
    fun femaleClothes():List<ClothesItemModel>{
        return femaleClothes
    }
    fun maleClothes():List<ClothesItemModel>{
        return maleClothes
    }
}

