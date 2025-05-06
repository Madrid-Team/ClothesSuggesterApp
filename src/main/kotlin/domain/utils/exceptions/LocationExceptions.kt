package domain.utils.exceptions

open class LocationExceptions(message: String) : ClothesSuggesterExceptions(message) {
    class LocationNotFoundException :
        LocationExceptions("The specified location could not be found. Please check the city name and try again.")

    class InvalidIpAddressException : LocationExceptions(
        "Invalid IP address. Unable to extract valid geographic coordinates from the provided input."
    )
}