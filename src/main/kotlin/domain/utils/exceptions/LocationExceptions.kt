package domain.utils.exceptions

open class LocationExceptions(message: String):ClothesSuggesterExceptions(message) {
    class LocationNotFoundException:LocationExceptions("The specified location could not be found. Please check the city name and try again.")
    class InvalidCoordinateFormatException	:LocationExceptions("Invalid coordinate format. Please provide valid latitude and longitude values.")
}