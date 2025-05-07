package domain.utils.exceptions

open class LocationException(message: String) : ClothesSuggesterExceptions(message) {
    class LocationNotFoundException :
        LocationException("The specified location could not be found. Please check the city name and try again.")

    class InvalidIpAddressException : LocationException(
        "Invalid IP address. Unable to extract valid geographic coordinates from the provided input."
    )

    class LocationDataException(message: String) : LocationException(message)
}