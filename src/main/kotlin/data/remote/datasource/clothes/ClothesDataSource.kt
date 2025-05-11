package data.remote.datasource.clothes

import data.remote.dtos.clothesDto.ClothesDto

interface ClothesDataSource {
    suspend fun getAllOutfit(weatherCode: String): ClothesDto
}