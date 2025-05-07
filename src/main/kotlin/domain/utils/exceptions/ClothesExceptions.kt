package domain.utils.exceptions


open class ClothesExceptions(message: String) : ClothesSuggesterExceptions(message) {
    class UnsupportedTemperatureRangeException :
        ClothesExceptions("The temperature is outside the supported range for outfit recommendations.")

    class MissingClothingDataException :
        ClothesExceptions("Clothing data is missing. Please make sure all necessary clothing information is available.")

    class OutfitNotFoundException :
        ClothesExceptions("Outfit Not Found Exception")

    class UnknownGenderException :
        ClothesExceptions("Unknown gender Exception")
}