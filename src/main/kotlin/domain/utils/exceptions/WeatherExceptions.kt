package domain.utils.exceptions

open class WeatherExceptions(message: String):ClothesSuggesterExceptions(message) {
    class WeatherApiFailedException:WeatherExceptions("Failed to fetch weather data from the API. Please try again later.")
    class WeatherDataOutOfRangeException:WeatherExceptions("The requested date is out of the supported weather forecast range.")
    class WeatherConditionNotSupportedException:WeatherExceptions("The current weather conditions are not supported for outfit suggestions.")
    class WeatherApiRateLimitException:WeatherExceptions("The weather service rate limit has been exceeded. Please wait and try again later.")
}