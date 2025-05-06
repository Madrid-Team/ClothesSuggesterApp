package domain.usecases.clothes

import domain.models.clothesModels.ClothesItemModel
import domain.models.clothesModels.ClothesModel
import domain.repositories.ClothesRepository
import domain.utils.exceptions.ClothesExceptions

class GetOutfitUseCase(private val clothesRepository: ClothesRepository) {
    suspend fun getDailyOutfit(temperature: Double, gender: String): ClothesModel {
        val outfit = clothesRepository.getDailyOutfit(temperature)
        return when (gender) {
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


    }
}