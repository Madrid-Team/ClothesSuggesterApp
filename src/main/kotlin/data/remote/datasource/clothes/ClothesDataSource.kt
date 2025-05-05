package data.remote.datasource.clothes

import data.remote.responsmodels.clothesModel.ClothesResponseModel

interface ClothesDataSource {
    fun getCurrentOutfit(weatherCode: String, gender: String): ClothesResponseModel
    fun getTomorrowOutfit(weatherCode: String, gender: String): ClothesResponseModel
    fun getWeeklyOutfit(weatherCode: String, gender: String): ClothesResponseModel
}