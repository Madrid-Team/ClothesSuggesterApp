package data.remote.datasource.clothes

import data.networking.constructUrl
import data.networking.safeCall
import data.remote.dtos.clothesDto.ClothesDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ClothesRemoteDataSource(
    private val httpClient: HttpClient
) : ClothesDataSource {
    override suspend fun getAllOutfit(weatherCode: String): ClothesDto =
        safeCall<ClothesDto> {
            httpClient.get(constructUrl(weatherCode))
        }

}