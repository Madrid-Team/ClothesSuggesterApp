package domain.utils.exceptions

open class WeatherException(message: String) : ClothesSuggesterExceptions(message) {
    class InvalidWeatherDataException(message: String) : WeatherException(message)
    class WeatherNetworkException(message: String) : WeatherException(message)
    class WeatherApiException(message: String) : WeatherException(message)
}