package presentation

import domain.models.locationModels.LocationModel
import domain.utils.Gender
import kotlinx.coroutines.*
import presentation.components.InputReader
import presentation.components.OutputPrinter
import presentation.features.clothes.GetOutfitCLI
import presentation.features.clothes.GetWeeklyOutfitCLI
import presentation.features.weather.GetCurrentWeatherCLI
import presentation.features.weather.GetWeeklyWeatherCLI

class ClothesSuggesterCLI(
    private val inputReader: InputReader,
    private val outputPrinter: OutputPrinter,
    private val getCurrentWeatherCLI: GetCurrentWeatherCLI,
    private val getOutfitCLI: GetOutfitCLI,
    private val getWeeklyWeatherCLI: GetWeeklyWeatherCLI,
    private val getWeeklyOutfitCLI: GetWeeklyOutfitCLI,
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
             "1" -> {
                 showTodayOutfit(gender)
              }
             "2" -> showWeeklyOutfit(gender)
             "3" -> showTomorrowOutfit(gender)
             else -> outputPrinter.printError("⚠️ Invalid option.")
         }



    }

    private fun showTomorrowOutfit(gender: Gender) {
        coroutineScope.launch {
            try {

                 outputPrinter.printMessage("🌤️ Checking current weather...")

                val weeklyWeather = withContext(Dispatchers.IO) {
                    getWeeklyWeatherCLI.getWeeklyWeather()
                }
                if (weeklyWeather.temperatureMax.size < 2) {
                    outputPrinter.printError("❌ Could not fetch tomorrow's weather.")
                    return@launch
                }

                val tomorrowTemp = weeklyWeather.temperatureMax[1]
                outputPrinter.printMessage("🌡️ Tomorrow's temperature: ${tomorrowTemp}°C")

                val category = getWeeklyWeatherCLI.getTemperatureCategory(tomorrowTemp)
                outputPrinter.printMessage("👕 Finding the perfect outfit...")

                val outfit = coroutineScope.async { getOutfitCLI.getOutfit(category, gender) }.await()
                outputPrinter.printMessage("👚 Your outfit suggestion for tomorrow:")
                outfit.forEach {
                    outputPrinter.printMessage("- ${it.title} (${it.description})")
                }
            } catch (e: Exception) {
                outputPrinter.printError("❌ Error: ${e.message}")
            }
        }
    }
    private fun showWeeklyOutfit(gender: Gender) {
        coroutineScope.launch {
            try {

                outputPrinter.printMessage("🌤️ Checking current weather...")
                val weeklyWeather =  getWeeklyWeatherCLI.getWeeklyWeather()

                outputPrinter.printMessage("👕 Finding the perfect outfit...")

                val tempCategories = getWeeklyWeatherCLI.getTemperatureCategories(weeklyWeather)
                val weeklyOutfit =  getWeeklyOutfitCLI.getWeeklyOutfit(tempCategories, gender)

                outputPrinter.printMessage("👚 Your outfit suggestions for the week:")

                weeklyOutfit.forEachIndexed { index, dayOutfit ->
                    val date = weeklyWeather.time[index] + " Day ${index + 1}"
                    val temp = weeklyWeather.temperatureMax[index]
                    outputPrinter.printMessage("\n📅 $date")
                    outputPrinter.printMessage("🌡️ Temperature: $temp°C")
                    dayOutfit.forEach {
                        outputPrinter.printMessage("- ${it.title} (${it.description})")
                    }
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
                    getCurrentWeatherCLI.getCurrentWeather()
                }.await()
                val tempCategory = getCurrentWeatherCLI.getTemperatureCategory(weather)

                 outputPrinter.printMessage("👕 Finding the perfect outfit...")
                val outfit = coroutineScope.async { getOutfitCLI.getOutfit(tempCategory, gender) }.await()


                outputPrinter.printMessage("🌡️ Current temperature: ${weather.temperature}°C")
                outputPrinter.printMessage("👗 Your suggested outfit for today:")
                outfit.forEach {
                    outputPrinter.printMessage("- ${it.title} (${it.description})")
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
        outputPrinter.printMessage("3. Tomorrow's Outfit 🌤️")
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


