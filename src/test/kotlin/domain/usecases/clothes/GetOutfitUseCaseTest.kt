package domain.usecases.clothes

import domain.models.clothesModels.ClothesItemModel
import domain.models.clothesModels.ClothesModel
import domain.repositories.ClothesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetOutfitUseCaseTest {
    lateinit var getOutfitUseCase: GetOutfitUseCase
    lateinit var clothesRepository: ClothesRepository

    @BeforeEach
    fun setUp() {
        clothesRepository = mockk(relaxed = true)
        getOutfitUseCase = GetOutfitUseCase(clothesRepository)
    }

    @Test
    fun `should return male winter clothes for low temperature`() {
        val testScope = TestScope()
        testScope.runTest {
            val temp = 5.0
            val gender = "male"
            val expected = ClothesModel(
                maleClothes = listOf(ClothesItemModel(id = 1, title = "", description = "Winter Jacket")),
                femaleClothes = emptyList()
            )
            coEvery { clothesRepository.getDailyOutfit(temp) } returns expected

            val result = getOutfitUseCase.getDailyOutfit(temp, gender)

            assertEquals(expected, result)
        }
    }

    @Test
    fun `should return female summer clothes for high temperature`() {
        val testScope = TestScope()
        testScope.runTest {
            val temp = 50.0
            val gender = "female"
            val expected = ClothesModel(
                maleClothes =  emptyList(),
                femaleClothes =listOf(ClothesItemModel(id = 1, title = "", description = "summer clothes"))
            )
            coEvery { clothesRepository.getDailyOutfit(temp) } returns expected

            val result = getOutfitUseCase.getDailyOutfit(temp, gender)

            assertEquals(expected, result)
        }
    }

}