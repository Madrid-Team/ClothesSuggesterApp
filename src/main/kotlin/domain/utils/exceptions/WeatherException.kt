package domain.utils.exceptions

open class WeatherException(message: String) : ClothesSuggesterExceptions(message) {
    class ForecastRangeException :
        WeatherException("The requested date is out of the supported weather forecast range.")

    class UnsupportedWeatherConditionException :
        WeatherException("The current weather conditions are not supported for outfit suggestions.")

    class WeatherDataException(message: String) : WeatherException(message)
}