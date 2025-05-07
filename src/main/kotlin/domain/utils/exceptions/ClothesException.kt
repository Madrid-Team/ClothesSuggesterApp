package domain.utils.exceptions


open class ClothesException(message: String) : ClothesSuggesterExceptions(message) {
    class UnsupportedTemperatureRangeException :
        ClothesException("The temperature is outside the supported range for outfit recommendations.")

    class ClothingDataException(message: String) : ClothesException(message)

    class UnknownGenderException(message: String) : ClothesException(message)

    class OutfitNotFoundException :
        ClothesException("Outfit Not Found Exception")
}