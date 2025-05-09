package domain.usecases.clothes

import domain.entities.clothesEntity.ClothesItem
import domain.entities.clothesEntity.Clothes
import domain.repositories.ClothesRepository
import domain.utils.Gender
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
    lateinit var testScope: TestScope

    @BeforeEach
    fun setUp() {
        clothesRepository = mockk(relaxed = true)
        getOutfitUseCase = GetOutfitUseCase(clothesRepository)
        testScope = TestScope()
    }

    @Test
    fun `should return male winter clothes for low temperature`() {
        testScope.runTest {
            val temp = "5.0"
            val gender = Gender.MALE
            val expected =Clothes(
                maleClothes = listOf(ClothesItem(id = 1, title = "", description = "Winter Jacket")),
                femaleClothes = emptyList()
            )

            coEvery { clothesRepository.getAllOutfit(temp) } returns expected

            val result = getOutfitUseCase.getDailyOutfit(temp, gender)

            assertEquals(expected.maleClothes, result)
        }
    }

    @Test
    fun `should return female summer clothes for high temperature`() {
        testScope.runTest {
            val temp = "50.0"
            val gender = Gender.FEMALE
            val expected = Clothes(
                maleClothes = emptyList(),
                femaleClothes = listOf(ClothesItem(id = 1, title = "", description = "summer clothes"))
            )
            coEvery { clothesRepository.getAllOutfit(temp) } returns expected

            val result = getOutfitUseCase.getDailyOutfit(temp, gender)

            assertEquals(expected.femaleClothes, result)
        }
    }

}