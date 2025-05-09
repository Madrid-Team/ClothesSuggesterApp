package presentation

import domain.usecases.clothes.GetOutfitUseCase
import domain.usecases.clothes.GetWeeklyOutfitUseCase
import domain.usecases.weather.GetCurrentWeatherUseCase
import domain.usecases.weather.GetWeeklyWeatherUseCase
import org.junit.jupiter.api.Assertions.*
import presentation.components.InputReader
import presentation.components.OutputPrinter
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ClothesSuggesterCLITest{
  private lateinit var inputReader: InputReader
  private lateinit var outputPrinter: OutputPrinter
  private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
  private lateinit var getOutfitUseCase: GetOutfitUseCase
  private lateinit var getWeeklyWeatherUseCase: GetWeeklyWeatherUseCase
  private lateinit var getWeeklyOutfitUseCase: GetWeeklyOutfitUseCase
  private lateinit var clothesSuggesterCLI: ClothesSuggesterCLI

  private val testDispatcher = StandardTestDispatcher()

  @BeforeEach
  fun setup() {
   inputReader = mockk(relaxed = true)
   outputPrinter = mockk(relaxed = true)
   getCurrentWeatherUseCase = mockk(relaxed = true)
   getOutfitUseCase = mockk(relaxed = true)
   getWeeklyWeatherUseCase = mockk(relaxed = true)
   getWeeklyOutfitUseCase = mockk(relaxed = true)

   clothesSuggesterCLI = ClothesSuggesterCLI(
    inputReader,
    outputPrinter,
    getCurrentWeatherUseCase,
    getOutfitUseCase,
    getWeeklyWeatherUseCase,
    getWeeklyOutfitUseCase,
    CoroutineScope(testDispatcher)
   )
  }

 }