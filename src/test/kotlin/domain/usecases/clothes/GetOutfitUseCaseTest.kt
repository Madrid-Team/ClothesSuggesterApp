package domain.usecases.clothes

import domain.repositories.ClothesRepository
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach

class GetOutfitUseCaseTest{
  lateinit var getOutfitUseCase: GetOutfitUseCase
  lateinit var clothesRepository: ClothesRepository

  @BeforeEach
  fun setUp() {
   clothesRepository = mockk(relaxed = true)
   getOutfitUseCase = GetOutfitUseCase(clothesRepository)
  }


 }