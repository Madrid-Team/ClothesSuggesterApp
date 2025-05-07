package presentation.features.location

import domain.models.locationModels.LocationModel
import domain.usecases.location.GetCurrentLocationUseCase
import domain.utils.exceptions.LocationException
import presentation.components.InputReader
import presentation.components.OutputPrinter

class GetCurrentLocationCLI(
    private val inputReader: InputReader,
    private val outputPrinter: OutputPrinter,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase
) {
    suspend fun getLocation(ip: String): LocationModel? {
        return try {
            getCurrentLocationUseCase.getCurrentLocation(ip)
        } catch (e: LocationException) {
            outputPrinter.printError( "${e.message}")
            null
        }
    }
}