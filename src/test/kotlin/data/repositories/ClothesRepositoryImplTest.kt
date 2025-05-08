package data.repositories

import data.remote.datasource.clothes.ClothesDataSource
import data.remote.responsmodels.clothesModel.ClothesItemResponseModel
import data.remote.responsmodels.clothesModel.ClothesResponseModel
import domain.models.clothesModels.ClothesItemModel
import domain.models.clothesModels.ClothesModel
import domain.utils.exceptions.ClothesException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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


    @Test
    fun `getAllOutfit returns correct ClothesModel when datasource succeeds`() = runTest {
        // Given
        val fakeResponse = ClothesResponseModel(
            maleClothes = listOf(
                ClothesItemResponseModel("desc1", 1, "T-Shirt")
            ),
            femaleClothes = listOf(
                ClothesItemResponseModel("desc2", 2, "Dress")
            )
        )

        coEvery { clothesDataSource.getAllOutfit("800") } returns fakeResponse

        // When
        val result = repository.getAllOutfit("800")

        // Then
        val expected = ClothesModel(
            femaleClothes = listOf(
                ClothesItemModel("desc2", 2, "Dress")
            ),
            maleClothes = listOf(
                ClothesItemModel("desc1", 1, "T-Shirt")
            )
        )

        assertEquals(expected, result)
    }


    @Test
    fun `getAllOutfit throws ClothesException when datasource fails`() = runTest {
        // Given
        coEvery { clothesDataSource.getAllOutfit(any()) } throws RuntimeException("Network error")
        // When & Then
        assertThrows<ClothesException.ClothesApiException> {
            repository.getAllOutfit("500")
        }
    }
}