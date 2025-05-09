package presentation

import domain.usecases.clothes.GetOutfitUseCase
import domain.usecases.clothes.GetWeeklyOutfitUseCase
import domain.usecases.weather.GetCurrentWeatherUseCase
import domain.usecases.weather.GetWeeklyWeatherUseCase
import domain.utils.Gender
import domain.utils.getTemperatureCategories
import domain.utils.getTemperatureCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import presentation.components.InputReader
import presentation.components.OutputPrinter
import presentation.utils.*

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
            else -> outputPrinter.printError(String.invalidOption)
        }


    }


    private fun showWeeklyOutfit(gender: Gender) {
        coroutineScope.launch {
            try {

                outputPrinter.printMessage(String.checkCurrentWeather)
                val weeklyWeather = getWeeklyWeatherUseCase.getWeeklyWeather()

                outputPrinter.printMessage(gender.getDressImoje() + String.perfectOutfit)

                val tempCategories = getTemperatureCategories(weeklyWeather)
                val weeklyOutfit = getWeeklyOutfitUseCase.getWeeklyOutfit(tempCategories, gender)

                outputPrinter.printMessage(String.weekSuggestions)

                weeklyOutfit.forEachIndexed { index, dayOutfit ->
                    val date = weeklyWeather.time[index] + " Day ${index + 1}"
                    val temp = weeklyWeather.temperatureMax[index]
                    outputPrinter.printMessage("\n📅 $date")
                    outputPrinter.printMessage(String.temperature.format(temp))
                    outputPrinter.printMessage(
                        "- ${dayOutfit[index].title.replace("+", "and")} \n\t${dayOutfit[index].description}\n")
                }
                outputPrinter.printMessage(String.recommendationComplete)

            } catch (e: Exception) {
                outputPrinter.printError(String.error.format(e))
            }
        }
    }

    private fun showTodayOutfit(gender: Gender) {

        coroutineScope.launch {
            try {

                outputPrinter.printMessage(String.checkCurrentWeather)
                val weather = getCurrentWeatherUseCase.getCurrentWeather()

                val tempCategory = getTemperatureCategory(weather.temperature)

                outputPrinter.printMessage(gender.getDressImoje() + String.perfectOutfit)
                val outfit = getOutfitUseCase.getDailyOutfit(tempCategory, gender)


                outputPrinter.printMessage(String.temperature.format(weather.temperature))
                outputPrinter.printMessage(gender.getDressImoje() + String.todaySuggestions)
                val randomOutfit = outfit.random()
                outputPrinter.printMessage(
                    "- ${randomOutfit.title.replace("+", "and")} \n\t${randomOutfit.description}\n")

                outputPrinter.printMessage(String.recommendationComplete)
            } catch (e: Exception) {
                outputPrinter.printError(String.error.format(e))
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
                outputPrinter.printError(String.invalidOption)
                println("Press Enter when you're done to exit...")
                false
            }
        }
    }
}


