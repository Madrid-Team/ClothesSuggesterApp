package presentation.utils

import domain.utils.Gender

val String.Companion.checkCurrentWeather: String
    get() =  "🌤️ Checking current weather..."

val String.Companion.perfectOutfit: String
    get() =  " Finding the perfect outfit..."

val String.Companion.weekSuggestions: String
    get() =  "👚 Your outfit suggestions for the week:"

val String.Companion.temperature: String
    get() =  "🌡️ Temperature: %s°C"

val String.Companion.todaySuggestions: String
    get() =  " Your suggested outfit for today:"

val String.Companion.recommendationComplete: String
    get() =  "✅ Outfit recommendation complete!"

val String.Companion.error: String
    get() =  "❌ Error: %s"
val String.Companion.invalidOption: String
    get() =  "⚠️ Invalid option."

fun Gender.getDressImoje():String{
    return when(this){
        Gender.MALE -> "👕"
        Gender.FEMALE -> "👗"
    }
}