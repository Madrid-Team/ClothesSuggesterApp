package domain.usecases.clothes

import domain.models.clothesModels.ClothesItemModel
import domain.models.clothesModels.ClothesModel
import domain.repositories.ClothesRepository
import domain.utils.Gender
import domain.utils.exceptions.ClothesExceptions

class GetOutfitUseCase(private val clothesRepository: ClothesRepository) {
    suspend fun getDailyOutfit(temperature: String, gender: String): List<ClothesItemModel> {
        return try {
            val outfit = clothesRepository.getAllOutfit(temperature)
             when (Gender.fromString(gender)) {
                Gender.MALE -> outfit.maleClothes
                Gender.FEMALE -> outfit.femaleClothes
            }
        } catch (e: ClothesExceptions) {
            throw e
        }


    }
}