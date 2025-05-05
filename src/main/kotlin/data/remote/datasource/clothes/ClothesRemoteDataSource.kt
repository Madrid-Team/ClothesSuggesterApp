package data.remote.datasource.clothes

import data.remote.responsmodels.clothesModel.ClothesResponseModel

class ClothesRemoteDataSource : ClothesDataSource {
    override suspend fun getAllOutfit(weatherCode: String): ClothesResponseModel {
        TODO("Not yet implemented")
    }

}