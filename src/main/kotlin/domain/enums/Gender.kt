package domain.enums

import domain.entities.clothesEntity.Clothes
import domain.entities.clothesEntity.ClothesItem

enum class Gender {
    MALE,
    FEMALE;
}

fun  Clothes.getClothesByGender(gender: Gender): List<ClothesItem> {
   return when (gender) {
        Gender.MALE -> this.maleClothes
        Gender.FEMALE -> this.femaleClothes
    }
}