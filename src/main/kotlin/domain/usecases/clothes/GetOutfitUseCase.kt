package domain.usecases.clothes

import domain.models.clothesModels.ClothesItemModel
import domain.repositories.ClothesRepository
import domain.utils.Gender
import domain.utils.exceptions.ClothesException

class GetOutfitUseCase(private val clothesRepository: ClothesRepository) {
    suspend fun getDailyOutfit(temperature: String, gender: Gender): List<ClothesItemModel> {
        return try {
            val outfit = clothesRepository.getAllOutfit(temperature)
             when (gender) {
                Gender.MALE -> outfit.maleClothes
                Gender.FEMALE -> outfit.femaleClothes
            }
        } catch (e: ClothesException) {
            throw e
        }



    }
}