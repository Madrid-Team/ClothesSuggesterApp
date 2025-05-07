package domain.usecases.clothes

import domain.models.clothesModels.ClothesItemModel
import domain.models.clothesModels.ClothesModel
import domain.repositories.ClothesRepository
import domain.utils.exceptions.ClothesExceptions
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetWeeklyOutfitUseCaseTest {
    private lateinit var getWeeklyOutfitUseCase: GetWeeklyOutfitUseCase
    private lateinit var clothesRepository: ClothesRepository
    private lateinit var testScope: TestScope

    @BeforeEach
    fun setUp() {
        clothesRepository = mockk(relaxed = true)
        getWeeklyOutfitUseCase = GetWeeklyOutfitUseCase(clothesRepository)
        testScope = TestScope()
    }


    @Test
    fun `should return weekly male outfits for different temperatures`() {
        testScope.runTest {
            val temperatures = listOf("5.0", "10.0", "15.0")
            val gender = "male"

            val expectedOutfits = listOf(
                listOf(ClothesItemModel(id = 1, title = "Day1", description = "Winter Jacket")),
                listOf(ClothesItemModel(id = 2, title = "Day2", description = "Coat")),
                listOf(ClothesItemModel(id = 3, title = "Day3", description = "Sweater"))
            )

            temperatures.forEachIndexed { index, temp ->
                val model = ClothesModel(
                    maleClothes = expectedOutfits[index],
                    femaleClothes = emptyList()
                )
                coEvery { clothesRepository.getAllOutfit(temp) } returns model
            }

            val result = getWeeklyOutfitUseCase.getWeeklyOutfit(temperatures, gender)

            assertEquals(expectedOutfits, result)
        }
    }

    @Test
    fun `should return weekly female outfits for different temperatures`() {
        testScope.runTest {
            val temperatures = listOf("25.0", "30.0", "35.0")
            val gender = "female"

            val expectedOutfits = listOf(
                listOf(ClothesItemModel(id = 4, title = "Day1", description = "Summer Dress")),
                listOf(ClothesItemModel(id = 5, title = "Day2", description = "Light Blouse")),
                listOf(ClothesItemModel(id = 6, title = "Day3", description = "T-shirt"))
            )

            temperatures.forEachIndexed { index, temp ->
                val model = ClothesModel(
                    maleClothes = emptyList(),
                    femaleClothes = expectedOutfits[index]
                )
                coEvery { clothesRepository.getAllOutfit(temp) } returns model
            }

            val result = getWeeklyOutfitUseCase.getWeeklyOutfit(temperatures, gender)

            assertEquals(expectedOutfits, result)
        }
    }

    @Test
    fun `should throw exception for unknown gender`() {
        testScope.runTest {
            val temperatures = listOf("20.0", "22.0")
            val gender = "robot"

            assertThrows<ClothesExceptions.UnknownGenderException> {
                getWeeklyOutfitUseCase.getWeeklyOutfit(temperatures, gender)
            }
        }
    }
}