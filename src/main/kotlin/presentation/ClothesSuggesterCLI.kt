package presentation

import data.utils.getTemperatureCategories
import data.utils.getTemperatureCategory
import domain.usecases.clothes.GetOutfitUseCase
import domain.usecases.clothes.GetWeeklyOutfitUseCase
import domain.usecases.weather.GetCurrentWeatherUseCase
import domain.usecases.weather.GetWeeklyWeatherUseCase
import domain.utils.Gender
import domain.utils.exceptions.ClothesException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import presentation.components.InputReader
import presentation.components.OutputPrinter

class ClothesSuggesterCLI(
    private val inputReader: InputReader,
    private val outputPrinter: OutputPrinter,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getOutfitUseCase: GetOutfitUseCase,
    private val getWeeklyWeatherUseCase: GetWeeklyWeatherUseCase,
    private val getWeeklyOutfitUseCase: GetWeeklyOutfitUseCase,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)

) {
    fun start() {
        printWelcomeMessage()

        if (!getUserConsent()) {
            return
        }

        val gender = getUserGender() ?: return


        val options = showMainMenu()

        when (options) {
            "1" -> showTodayOutfit(gender)
            "2" -> showWeeklyOutfit(gender)
            else -> outputPrinter.printError("⚠️ Invalid option.")
        }


    }


    private fun showWeeklyOutfit(gender: Gender) {
        coroutineScope.launch {
            try {

                outputPrinter.printMessage("🌤️ Checking current weather...")
                val weeklyWeather = getWeeklyWeatherUseCase.getWeeklyWeather()

                outputPrinter.printMessage("👕 Finding the perfect outfit...")

                val tempCategories = getTemperatureCategories(weeklyWeather)
                val weeklyOutfit = getWeeklyOutfitUseCase.getWeeklyOutfit(tempCategories, gender)

                outputPrinter.printMessage("👚 Your outfit suggestions for the week:")

                weeklyOutfit.forEachIndexed { index, dayOutfit ->
                    val date = weeklyWeather.time[index] + " Day ${index + 1}"
                    val temp = weeklyWeather.temperatureMax[index]
                    outputPrinter.printMessage("\n📅 $date")
                    outputPrinter.printMessage("🌡️ Temperature: $temp°C")
                    outputPrinter.printMessage("- ${dayOutfit[index].title} (${dayOutfit[index].description})")

                }
            } catch (e: Exception) {
                outputPrinter.printError("❌ Error: ${e.message}")
            }
        }
    }

    private fun showTodayOutfit(gender: Gender) {

        coroutineScope.launch {
            try {

                outputPrinter.printMessage("🌤️ Checking current weather...")
                val weather = coroutineScope.async {
                    getCurrentWeatherUseCase.getCurrentWeather()
                }.await()
                val tempCategory = getTemperatureCategory(weather.temperature)

                outputPrinter.printMessage("👕 Finding the perfect outfit...")
                val outfit = coroutineScope.async { getOutfitUseCase.getDailyOutfit(tempCategory, gender) }.await()


                outputPrinter.printMessage("🌡️ Current temperature: ${weather.temperature}°C")
                outputPrinter.printMessage("👗 Your suggested outfit for today:")
                val randomOutfit = outfit.random()
                outputPrinter.printMessage("- ${randomOutfit.title} (${randomOutfit.description})")
                val option = inputReader.readInput("- Enter 1 for another suggestion or enter to exit")
                when (option) {
                    "1" -> outputPrinter.printMessage("- ${randomOutfit.title} (${randomOutfit.description})")
                    else -> outputPrinter.printError("❌ Error: Invalid option.")
                }

                outputPrinter.printMessage("✅ Outfit recommendation complete!")
            } catch (e: Exception) {
                outputPrinter.printError("❌ Error: ${e.message}")
            }
        }

    }


    private fun showMainMenu(): String {
        outputPrinter.printMessage("What would you like to see?")
        outputPrinter.printMessage("1. Today's Outfit 👕")
        outputPrinter.printMessage("2. Weekly Outfit 📅")
        return inputReader.readInput("Enter your choice: ")
    }


    private fun getUserGender(): Gender? {
        outputPrinter.printMessage("Please select your gender 👤:")
        outputPrinter.printMessage("1. Male ♂️")
        outputPrinter.printMessage("2. Female ♀️")

        val genderInput = inputReader.readInput("Enter 1 or 2: ")
        return when (genderInput) {
            "1" -> Gender.MALE
            "2" -> Gender.FEMALE
            else -> {
                outputPrinter.printError("⚠️ Invalid gender input.")
                println("Press Enter when you're done to exit...")

                null
            }
        }
    }

    private fun printWelcomeMessage() {
        outputPrinter.printMessage("===============================================")
        outputPrinter.printMessage("===============================================")
        outputPrinter.printMessage("=== Welcome to Clothes Suggester App 😌 👋 ===")
        outputPrinter.printMessage("===============================================")
        outputPrinter.printMessage("===============================================")
    }

    private fun getUserConsent(): Boolean {
        outputPrinter.printMessage("To suggest outfits, I need your location. 📍")
        outputPrinter.printMessage("Enter 1 to agree ✅  or 0 to exit ❌.")

        return when (inputReader.readInput("Select an option: ")) {
            "1" -> true
            "0" -> {
                outputPrinter.printMessage("Application terminated. 👋")
                false
            }

            else -> {
                outputPrinter.printError("⚠️ Invalid option.")
                println("Press Enter when you're done to exit...")
                false
            }
        }
    }
}


