package domain.usecases.clothes

import domain.models.clothesModels.ClothesItemModel
import domain.models.clothesModels.ClothesModel
import domain.repositories.ClothesRepository
import domain.utils.exceptions.ClothesExceptions

class GetOutfitUseCase(private val clothesRepository: ClothesRepository) {
    suspend fun getDailyOutfit(temperature: String, gender: String): List<ClothesItemModel> {
        return try {
            val outfit = clothesRepository.getAllOutfit(temperature)
            when (gender.lowercase()) {
                "male" -> outfit.maleClothes

                "female" -> outfit.femaleClothes

                else -> throw ClothesExceptions.UnknownGenderException()
            }
        } catch (e: ClothesExceptions) {
            throw e
        }


    }
}