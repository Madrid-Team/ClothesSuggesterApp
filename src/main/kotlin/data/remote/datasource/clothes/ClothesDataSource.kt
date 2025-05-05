package data.remote.datasource.clothes

import data.remote.responsmodels.clothesModel.ClothesResponseModel

interface ClothesDataSource {
    fun getCurrentOutfit(weatherCode: String): ClothesResponseModel
    fun getTomorrowOutfit(weatherCode: String): ClothesResponseModel
    fun getWeeklyOutfit(weatherCode: String): ClothesResponseModel
}