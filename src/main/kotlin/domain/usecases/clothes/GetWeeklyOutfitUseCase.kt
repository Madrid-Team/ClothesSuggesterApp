package domain.usecases.clothes

import domain.models.clothesModels.ClothesItemModel
import domain.repositories.ClothesRepository

class GetWeeklyOutfitUseCase(private val clothesRepository: ClothesRepository) {
    suspend fun getWeeklyOutfit(tempList: List<String>, gender: String): List<List<ClothesItemModel>>{
        return emptyList()
    }
}