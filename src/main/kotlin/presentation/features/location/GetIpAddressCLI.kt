package presentation.features.location

import domain.models.locationModels.IpAddressModel
import domain.usecases.location.GetIpAddressUseCase
import domain.utils.exceptions.LocationException
import presentation.components.InputReader
import presentation.components.OutputPrinter

class GetIpAddressCLI(
    private val inputReader: InputReader,
    private val outputPrinter: OutputPrinter,
    private val getIpAddressUseCase: GetIpAddressUseCase
) {
    suspend fun getIpAddress(): IpAddressModel? {
        return try {
            getIpAddressUseCase.getIpAddress()
        } catch (e: LocationException) {
            outputPrinter.printError(" ${e.message}")
            null
        }
    }
}

