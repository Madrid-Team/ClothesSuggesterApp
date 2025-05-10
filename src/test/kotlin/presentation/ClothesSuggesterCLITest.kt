package presentation

import domain.entities.clothesEntity.ClothesItem
import domain.entities.weatherEntity.CurrentWeather
import domain.entities.weatherEntity.DailyWeather
import domain.usecases.clothes.GetOutfitUseCase
import domain.usecases.clothes.GetWeeklyOutfitUseCase
import domain.usecases.weather.GetCurrentWeatherUseCase
import domain.usecases.weather.GetWeeklyWeatherUseCase
import domain.usecases.weather.createCurrentWeather
import domain.usecases.weather.createWeatherModel
import org.junit.jupiter.api.Assertions.*
import presentation.components.InputReader
import presentation.components.OutputPrinter
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import io.mockk.verify
import presentation.utils.invalidOption
import presentation.utils.recommendationComplete

class ClothesSuggesterCLITest {
    private lateinit var inputReader: InputReader
    private lateinit var outputPrinter: OutputPrinter
    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    private lateinit var getOutfitUseCase: GetOutfitUseCase
    private lateinit var getWeeklyWeatherUseCase: GetWeeklyWeatherUseCase
    private lateinit var getWeeklyOutfitUseCase: GetWeeklyOutfitUseCase
    private lateinit var clothesSuggesterCLI: ClothesSuggesterCLI
    lateinit var testScope: TestScope

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
        testScope = TestScope()

    }
    @Test
    fun `should print welcome message when app starts`() {
        every { inputReader.readInput(any()) } returnsMany listOf("1", "1", "0")
        val weather = createCurrentWeather(
            temperature = 24.0,
            time = "2025-05-09T14:00",
        )
        coEvery { getCurrentWeatherUseCase.getCurrentWeather() } returns weather


        coEvery { getOutfitUseCase.getDailyOutfit(any(), any()) } returns listOf(
            ClothesItem("T-shirt", 1, "A cool cotton T-shirt")
        )

        clothesSuggesterCLI.start()
        testDispatcher.scheduler.advanceUntilIdle()

        verify { outputPrinter.printMessage(match { it.contains("Welcome to Clothes Suggester") }) }
    }


    @Test
    fun `should show today's outfit when user selects option 1`() {
        every { inputReader.readInput(any()) } returnsMany listOf("1", "1", "1")
        val weather = createCurrentWeather(
            temperature = 24.0,
            time = "2025-05-09T14:00",
        )
        coEvery { getCurrentWeatherUseCase.getCurrentWeather() } returns weather
        coEvery { getOutfitUseCase.getDailyOutfit(any(), any()) } returns listOf(
            ClothesItem("T-shirt", 1, "Cool and comfy.")
        )

        clothesSuggesterCLI.start()
        testDispatcher.scheduler.advanceUntilIdle()

        verify { outputPrinter.printMessage(match { it.contains("T-shirt") }) }
    }


    @Test
    fun `should show weekly outfit when user selects option 2`() {
        every { inputReader.readInput(any()) } returnsMany listOf("1", "1", "2")

        val weeklyWeather = DailyWeather(
            temperatureMax = listOf(22.0, 25.0, 28.0, 30.0, 26.0, 24.0, 23.0),
            temperatureMin = listOf(15.0, 17.0, 18.0, 19.0, 17.0, 16.0, 15.0),
            time = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
            weatherCode = listOf(1, 1, 1, 1, 1, 1, 1)
        )

        coEvery { getWeeklyWeatherUseCase.getWeeklyWeather() } returns weeklyWeather

        coEvery { getWeeklyOutfitUseCase.getWeeklyOutfit(any(), any()) } returns List(7) {
            listOf(
                ClothesItem("Shirt", 1, "Casual shirt")
            )
        }

        clothesSuggesterCLI.start()
        testDispatcher.scheduler.advanceUntilIdle()

        verify { outputPrinter.printMessage(match { it.contains("Shirt") }) }
    }


    @Test
    fun `should print error message for invalid gender input`() {
        every { inputReader.readInput(any()) } returnsMany listOf("1", "3")

        clothesSuggesterCLI.start()

        verify { outputPrinter.printError("⚠️ Invalid gender input.") }
    }

    @Test
    fun `should terminate when user doesn't consent`() {
        every { inputReader.readInput(any()) } returns "0" // no consent

        clothesSuggesterCLI.start()

        verify { outputPrinter.printMessage("Application terminated. 👋") }
    }

    @Test
    fun `should print error message on exception in showTodayOutfit`() {
        every { inputReader.readInput(any()) } returnsMany listOf("1", "1", "1")
        coEvery { getCurrentWeatherUseCase.getCurrentWeather() } throws RuntimeException("Something went wrong")

        clothesSuggesterCLI.start()
        testDispatcher.scheduler.advanceUntilIdle()

        verify { outputPrinter.printError(match { it.contains("Something went wrong") }) }
    }

    @Test
    fun `should print error message for invalid menu option`() {
        every { inputReader.readInput(any()) } returnsMany listOf("1", "1", "5")

        clothesSuggesterCLI.start()

        verify { outputPrinter.printError(String.invalidOption) }

    }
    @Test
    fun `should print error and exit message when user enters invalid consent option`() {
        every { inputReader.readInput(any()) } returns "invalid"

        clothesSuggesterCLI.start()

        verify { outputPrinter.printError(String.invalidOption) }
        verify { outputPrinter.printMessage("Press Enter when you're done to exit...") }
    }


    @Test
    fun `should print recommendation complete message for today's outfit`() {
        every { inputReader.readInput(any()) } returnsMany listOf("1", "1", "1")
        val weather = createCurrentWeather(
            temperature = 24.0,
            time = "2025-05-09T14:00",
        )
        coEvery { getCurrentWeatherUseCase.getCurrentWeather() } returns weather
        coEvery { getOutfitUseCase.getDailyOutfit(any(), any()) } returns listOf(
            ClothesItem("Jacket", 1, "Warm and stylish.")
        )

        clothesSuggesterCLI.start()
        testDispatcher.scheduler.advanceUntilIdle()

        verify { outputPrinter.printMessage(String.recommendationComplete) }
    }
    @Test
    fun `should print recommendation complete and fireworks after today outfit`() = runTest {
        every { inputReader.readInput(any()) } returnsMany listOf("1", "1", "1")
        val weather = createCurrentWeather(
            temperature = 24.0,
            time = "2025-05-09T14:00",
        )
        coEvery { getCurrentWeatherUseCase.getCurrentWeather() } returns weather
        coEvery { getOutfitUseCase.getDailyOutfit(any(), any()) } returns listOf(
            ClothesItem("Sweater", 1, "Warm and cozy.")
        )

        clothesSuggesterCLI.start()
        testDispatcher.scheduler.advanceUntilIdle()

        verify { outputPrinter.printMessage(String.recommendationComplete) }

        val fireworksLines = listOf(
            "       🎆        🎇        🎆",
            "    *     *   *     *   *     *",
            "  *   💥   * *  💫  * *   🎉  *",
            "    *     *   *     *   *     *",
            "       🎆        🎇        🎆",
        )
        fireworksLines.forEach {
            verify { outputPrinter.printMessage(it) }
        }
    }


}
