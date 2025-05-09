package domain.usecases.clothes

import domain.entities.clothesEntity.ClothesItem
import domain.repositories.ClothesRepository
import domain.utils.Gender

class GetWeeklyOutfitUseCase(private val clothesRepository: ClothesRepository) {
    suspend fun getWeeklyOutfit(tempList: List<String>, gender: Gender): List<List<ClothesItem>> {
        return tempList.map { temp ->
            val outfit = clothesRepository.getAllOutfit(temp)
            when (gender) {
                Gender.MALE -> outfit.maleClothes
                Gender.FEMALE -> outfit.femaleClothes
            }
        }
    }
}