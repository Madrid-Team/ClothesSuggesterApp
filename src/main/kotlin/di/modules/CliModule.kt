package di.modules


import org.koin.dsl.module
import presentation.ClothesSuggesterCLI
import presentation.components.ConsoleInputReader
import presentation.components.ConsoleOutputPrinter
import presentation.components.InputReader
import presentation.components.OutputPrinter
import presentation.features.clothes.GetOutfitCLI
import presentation.features.weather.GetCurrentWeatherCLI

val cliModule = module {
    single<InputReader> { ConsoleInputReader() }
    single<OutputPrinter> { ConsoleOutputPrinter() }
    single { GetOutfitCLI(get(), get(), get()) }
    single { GetCurrentWeatherCLI(get(), get(), get()) }
    single { ClothesSuggesterCLI(get(), get(), get(), get(), get(), get()) }
}