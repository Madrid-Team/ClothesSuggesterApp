package data.repositories

import data.remote.datasource.clothes.ClothesDataSource
import data.utils.toClothesExceptions
import domain.entities.clothesEntity.Clothes
import domain.repositories.ClothesRepository

class ClothesRepositoryImpl(
    private val clothesDataSource: ClothesDataSource,

) : ClothesRepository {
    override suspend fun getAllOutfit(weatherCode: String): Clothes {
        try {
            val result = clothesDataSource.getAllOutfit(weatherCode)
            return result.toClothes()

        } catch (exception: Exception) {
            throw exception.toClothesExceptions()
        }
    }


}