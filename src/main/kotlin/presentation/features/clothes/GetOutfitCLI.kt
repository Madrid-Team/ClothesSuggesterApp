package presentation.features.clothes

import domain.models.clothesModels.ClothesItemModel
import domain.usecases.clothes.GetOutfitUseCase
import domain.utils.Gender
import domain.utils.exceptions.ClothesException
import domain.utils.exceptions.WeatherException
import presentation.components.InputReader
import presentation.components.OutputPrinter

class GetOutfitCLI(
    private val inputReader: InputReader,
    private val outputPrinter: OutputPrinter,
    private val getOutfitUseCase: GetOutfitUseCase
) {
    suspend fun getOutfit(temp:String, gender:Gender): List<ClothesItemModel>? {
        return try {
            getOutfitUseCase.getDailyOutfit(temp,gender)
        } catch (e: ClothesException) {
            outputPrinter.printError("${e.message}")
            null
        }
    }
}