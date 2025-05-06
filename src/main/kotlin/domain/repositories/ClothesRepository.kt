package domain.repositories

import domain.models.clothesModels.ClothesModel

interface ClothesRepository {
    suspend fun getAllOutfit(weatherCode: String): ClothesModel
}