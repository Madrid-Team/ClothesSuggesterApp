package data.repositories

import data.remote.datasource.clothes.ClothesRemoteDataSource
import data.utils.toClothesExceptions
import domain.models.clothesModels.ClothesModel
import domain.repositories.ClothesRepository

class ClothesRepositoryImpl(
    private val clothesRemoteDataSource: ClothesRemoteDataSource
) : ClothesRepository {
    override suspend fun getAllOutfit(weatherCode: String): ClothesModel {
        try {
            val result = clothesRemoteDataSource.getAllOutfit(weatherCode)
            return result.toClothesModel()

        } catch (exception: Exception) {
            throw exception.toClothesExceptions()
        }
    }
}