package data.repositories

import data.remote.datasource.clothes.ClothesDataSource
import data.remote.datasource.location.LocationDataSource
import data.utils.toClothesExceptions
import data.utils.toLocationExceptions
import domain.models.clothesModels.ClothesModel
import domain.models.locationModels.IpAddressModel
import domain.models.locationModels.LocationModel
import domain.repositories.ClothesRepository

class ClothesRepositoryImpl(
    private val clothesDataSource: ClothesDataSource,

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