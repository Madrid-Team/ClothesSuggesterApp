package presentation.features.clothes

import domain.models.clothesModels.ClothesItemModel
import domain.usecases.clothes.GetWeeklyOutfitUseCase
import domain.utils.Gender
import domain.utils.exceptions.ClothesException
import presentation.components.InputReader
import presentation.components.OutputPrinter

class GetWeeklyOutfitCLI(
    private val inputReader: InputReader,
    private val outputPrinter: OutputPrinter,
    private val getWeeklyOutfitUseCase: GetWeeklyOutfitUseCase
) {
    suspend fun getWeeklyOutfit(tempList: List<String>, gender: Gender): List<List<ClothesItemModel>>? {
        return try {
            getWeeklyOutfitUseCase.getWeeklyOutfit(tempList,gender)
        } catch (e: ClothesException) {
            outputPrinter.printError("${e.message}")
            null
        }
    }
}