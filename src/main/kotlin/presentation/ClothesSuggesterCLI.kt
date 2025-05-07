package presentation

import domain.utils.Gender
import presentation.components.InputReader
import presentation.components.OutputPrinter
import presentation.features.clothes.GetOutfitCLI
import presentation.features.clothes.GetWeeklyOutfitCLI
import presentation.features.location.GetCurrentLocationCLI
import presentation.features.location.GetIpAddressCLI
import presentation.features.weather.GetCurrentWeatherCLI
import presentation.features.weather.GetWeeklyWeatherCLI

class ClothesSuggesterCLI(
    private val inputReader: InputReader,
    private val outputPrinter: OutputPrinter,
    private val getIpAddressCLI: GetIpAddressCLI,
    private val getCurrentLocationCLI: GetCurrentLocationCLI,
    private val getCurrentWeatherCLI: GetCurrentWeatherCLI,
    private val getOutfitCLI: GetOutfitCLI,
    private val getWeeklyWeatherCLI: GetWeeklyWeatherCLI,
    private val getWeeklyOutfitCLI: GetWeeklyOutfitCLI
) {
    suspend fun start() {
        outputPrinter.printMessage("===============================================")
        outputPrinter.printMessage("===============================================")
        outputPrinter.printMessage("=== Welcome to Clothes Suggester App 😌 👋 ===")
        outputPrinter.printMessage("===============================================")
        outputPrinter.printMessage("===============================================")


        // Step 1: Location Consent
        outputPrinter.printMessage("To suggest outfits, I need your location. 📍")
        outputPrinter.printMessage("Enter 1 to agree ✅  or 0 to exit ❌.")
        when (inputReader.readInput("Select an option: ")) {
            "1" -> {}
            "0" -> {
                outputPrinter.printMessage("Application terminated. 👋")
                return
            }

            else -> {
                outputPrinter.printError("⚠️ Invalid option.")
                return
            }
        }

        outputPrinter.printMessage("Please select your gender 👤:")
        outputPrinter.printMessage("1. Male ♂️")
        outputPrinter.printMessage("2. Female ♀️")

        val genderInput = inputReader.readInput("Enter 1 or 2: ")
        val gender = when (genderInput) {
            "1" -> Gender.MALE
            "2" -> Gender.FEMALE
            else -> {
                outputPrinter.printError("⚠️ Invalid gender input.")
                return
            }
        }

        val ipModel = getIpAddressCLI.getIpAddress()
        val location = ipModel?.ipAddress?.let { getCurrentLocationCLI.getLocation(it) }
        if (location == null) {
            outputPrinter.printError("❌ Failed to determine location.")
            return
        }

        outputPrinter.printMessage("What would you like to see?")
        outputPrinter.printMessage("1. Today's Outfit 👕")
        outputPrinter.printMessage("2. Weekly Outfit 📅")
        outputPrinter.printMessage("3. Tomorrow's Outfit 🌤️")
        val option = inputReader.readInput("Enter your choice: ")

        when (option) {
            "1" -> {
                val weather = getCurrentWeatherCLI.getCurrentWeather(location.latitude, location.longitude)
                val tempCategory = getCurrentWeatherCLI.getTemperatureCategory(weather)
                val outfit = getOutfitCLI.getOutfit(tempCategory, gender)

                if (outfit != null) {
                    outputPrinter.printMessage("🌡️ Current temperature: ${weather?.temperature}°C")
                    outputPrinter.printMessage("👗 Your suggested outfit for today:")
                    outfit.forEach {
                        outputPrinter.printMessage("- ${it.title} (${it.description})")
                    }
                } else {
                    outputPrinter.printError("❌ Could not fetch outfit.")
                }
            }

            "2" -> {
                val weeklyWeather = getWeeklyWeatherCLI.getWeeklyWeather(location.latitude, location.longitude)
                if (weeklyWeather == null) {
                    outputPrinter.printError("❌ Could not fetch weekly weather.")
                    return
                }

                val tempCategories = getWeeklyWeatherCLI.getTemperatureCategories(weeklyWeather)
                val weeklyOutfit = getWeeklyOutfitCLI.getWeeklyOutfit(tempCategories, gender)

                if (weeklyOutfit != null) {
                    outputPrinter.printMessage("👚 Your outfit suggestions for the week:")

                    weeklyOutfit.forEachIndexed { index, dayOutfit ->
                        val date = weeklyWeather.time.getOrNull(index) ?: "Day ${index + 1}"
                        val temp = weeklyWeather.temperatureMax.getOrNull(index) ?: "-"
                        outputPrinter.printMessage("\n📅 $date")
                        outputPrinter.printMessage("🌡️ Temperature: $temp°C")
                        dayOutfit.forEach {
                            outputPrinter.printMessage("- ${it.title} (${it.description})")
                        }
                    }
                } else {
                    outputPrinter.printError("❌ Could not fetch weekly outfit.")
                }
            }
            "3" -> {
                val weeklyWeather = getWeeklyWeatherCLI.getWeeklyWeather(location.latitude, location.longitude)
                if (weeklyWeather == null || weeklyWeather.temperatureMax.size < 2) {
                    outputPrinter.printError("❌ Could not fetch tomorrow's weather.")
                    return
                }

                val tomorrowTemp = weeklyWeather.temperatureMax[1]
                outputPrinter.printMessage("🌡️ Tomorrow's temperature: ${tomorrowTemp}°C")

                val category = getWeeklyWeatherCLI.getTemperatureCategory(tomorrowTemp)

                val outfit = getOutfitCLI.getOutfit(category, gender)
                if (outfit != null) {
                    outputPrinter.printMessage("👚 Your outfit suggestion for tomorrow:")
                    outfit.forEach {
                        outputPrinter.printMessage("- ${it.title} (${it.description})")
                    }
                } else {
                    outputPrinter.printError("❌ Could not fetch outfit for tomorrow.")
                }
            }



            else -> outputPrinter.printError("⚠️ Invalid option.")
        }
    }
}
