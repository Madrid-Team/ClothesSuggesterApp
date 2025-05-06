package data.repositories

import data.remote.datasource.clothes.ClothesDataSource
import data.utils.toClothesExceptions
import domain.models.clothesModels.ClothesModel
import domain.repositories.ClothesRepository

class ClothesRepositoryImpl(
    private val clothesDataSource: ClothesDataSource
) : ClothesRepository {
    override suspend fun getAllOutfit(weatherCode: String): ClothesModel {
        try {
            val result = clothesDataSource.getAllOutfit(weatherCode)
            return result.toClothesModel()

        } catch (exception: Exception) {
            throw exception.toClothesExceptions()
        }
    }
}