package data.remote.dtos.clothesDto

import domain.entities.clothesEntity.Clothes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClothesDto(
    @SerialName("female")
    val femaleClothes: List<ClothesItemDto>,
    @SerialName("male")
    val maleClothes: List<ClothesItemDto>
) {
    fun toClothes(): Clothes =
        Clothes(
            femaleClothes = this.femaleClothes.map { it.toClothesItem() },
            maleClothes = this.maleClothes.map { it.toClothesItem() }
        )
}