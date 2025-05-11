package domain.usecases.clothes

import domain.entities.clothesEntity.Clothes
import domain.entities.clothesEntity.ClothesItem
import domain.repositories.ClothesRepository
import domain.enums.Gender
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
            val gender = Gender.MALE

            val expectedOutfits = listOf(
                listOf(ClothesItem(id = 1, title = "Day1", description = "Winter Jacket")),
                listOf(ClothesItem(id = 2, title = "Day2", description = "Coat")),
                listOf(ClothesItem(id = 3, title = "Day3", description = "Sweater"))
            )

            temperatures.forEachIndexed { index, temp ->
                val model = Clothes(
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
            val gender = Gender.FEMALE

            val expectedOutfits = listOf(
                listOf(ClothesItem(id = 4, title = "Day1", description = "Summer Dress")),
                listOf(ClothesItem(id = 5, title = "Day2", description = "Light Blouse")),
                listOf(ClothesItem(id = 6, title = "Day3", description = "T-shirt"))
            )

            temperatures.forEachIndexed { index, temp ->
                val model = Clothes(
                    maleClothes = emptyList(),
                    femaleClothes = expectedOutfits[index]
                )
                coEvery { clothesRepository.getAllOutfit(temp) } returns model
            }

            val result = getWeeklyOutfitUseCase.getWeeklyOutfit(temperatures, gender)

            assertEquals(expectedOutfits, result)
        }
    }

}