package data.remote.datasource.clothes

import data.remote.responsmodels.clothesModel.ClothesResponseModel

class ClothesRemoteDataSource : ClothesDataSource {
    override fun getCurrentOutfit(weatherCode: String): ClothesResponseModel {
        TODO("Not yet implemented")
    }

    override fun getTomorrowOutfit(weatherCode: String): ClothesResponseModel {
        TODO("Not yet implemented")
    }

    override fun getWeeklyOutfit(weatherCode: String): ClothesResponseModel {
        TODO("Not yet implemented")
    }

}