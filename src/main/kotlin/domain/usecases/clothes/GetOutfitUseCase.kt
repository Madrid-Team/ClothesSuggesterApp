package domain.usecases.clothes

import domain.models.clothesModels.ClothesModel
import domain.repositories.ClothesRepository
import domain.utils.exceptions.ClothesExceptions

class GetOutfitUseCase(private val clothesRepository: ClothesRepository) {
    suspend fun getDailyOutfit(temperature: Double, gender: String): ClothesModel {
        return try {
            val outfit = clothesRepository.getDailyOutfit(temperature)
            when (gender.lowercase()) {
                "male" -> ClothesModel(
                    maleClothes = outfit.maleClothes,
                    femaleClothes = emptyList()
                )

                "female" -> ClothesModel(
                    maleClothes = emptyList(),
                    femaleClothes = outfit.femaleClothes
                )

                else -> throw ClothesExceptions.UnknownGenderException()
            }
        } catch (e: ClothesExceptions.UnknownGenderException) {
            throw e
        }


    }
}