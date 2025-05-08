package data.repositories

import data.remote.datasource.clothes.ClothesDataSource
import data.remote.responsmodels.clothesModel.ClothesItemResponseModel
import data.remote.responsmodels.clothesModel.ClothesResponseModel
import domain.models.clothesModels.ClothesItemModel
import domain.models.clothesModels.ClothesModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ClothesRepositoryImplTest {
    private lateinit var clothesDataSource: ClothesDataSource
    private lateinit var repository: ClothesRepositoryImpl

    @BeforeEach
    fun setup() {
        clothesDataSource = mockk(relaxed = true)
        repository = ClothesRepositoryImpl(
            clothesDataSource
        )
    }


    
}