package domain.usecases.clothes

import domain.models.clothesModels.ClothesItemModel
import domain.repositories.ClothesRepository
import domain.utils.Gender
import domain.utils.exceptions.ClothesExceptions

class GetWeeklyOutfitUseCase(private val clothesRepository: ClothesRepository) {
    suspend fun getWeeklyOutfit(tempList: List<String>, gender: String): List<List<ClothesItemModel>>{
        val genderEnum = try {
            Gender.fromString(gender)
        } catch (e: Exception) {
            throw ClothesExceptions.UnknownGenderException()
        }

        return tempList.map { temp ->
            val outfit = clothesRepository.getAllOutfit(temp)
            when (genderEnum) {
                Gender.MALE -> outfit.maleClothes
                Gender.FEMALE -> outfit.femaleClothes
            }
        }
    }
}