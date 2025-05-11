package domain.utils.exceptions


open class ClothesException(message: String) : ClothesSuggesterExceptions(message) {
    class InvalidClothesDataException(message: String) : ClothesException(message)
    class ClothesNetworkException(message: String) : ClothesException(message)
    class ClothesApiException(message: String) : ClothesException(message)
}