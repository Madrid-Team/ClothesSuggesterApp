package domain.usecases.clothes

import domain.entities.clothesEntity.ClothesItem
import domain.repositories.ClothesRepository
import domain.enums.Gender
import domain.utils.exceptions.ClothesException
import domain.enums.getClothesByGender

class GetOutfitUseCase(private val clothesRepository: ClothesRepository) {
    suspend fun getDailyOutfit(temperature: String, gender: Gender): List<ClothesItem> {
        return try {
            val outfit = clothesRepository.getAllOutfit(temperature)
             outfit.getClothesByGender(gender)
        } catch (e: ClothesException) {
            throw e
        }

    }
}