package domain.repositories

import domain.entities.clothesEntity.Clothes

interface ClothesRepository {
    suspend fun getAllOutfit(weatherCode: String): Clothes
}