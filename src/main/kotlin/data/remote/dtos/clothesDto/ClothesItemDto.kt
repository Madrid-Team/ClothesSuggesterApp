package data.remote.dtos.clothesDto

import domain.entities.clothesEntity.ClothesItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClothesItemDto(
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String
){
    fun toClothesItem(): ClothesItem =
        ClothesItem(
            id = this.id,
            description = this.description,
            title = this.title
        )
}