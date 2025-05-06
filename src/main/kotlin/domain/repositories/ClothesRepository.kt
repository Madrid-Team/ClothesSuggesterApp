package domain.repositories

import domain.models.clothesModels.ClothesModel

interface ClothesRepository {
    suspend fun getDailyOutfit(temperature: Double): ClothesModel

    }