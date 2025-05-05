package data.remote.datasource.clothes

import data.remote.responsmodels.clothesModel.ClothesResponseModel

interface ClothesDataSource {
    suspend fun getAllOutfit(weatherCode: String): ClothesResponseModel
}