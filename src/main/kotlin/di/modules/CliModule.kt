package di.modules


import org.koin.dsl.module
import presentation.ClothesSuggesterCLI
import presentation.components.ConsoleInputReader
import presentation.components.ConsoleOutputPrinter
import presentation.components.InputReader
import presentation.components.OutputPrinter
import presentation.features.clothes.GetOutfitCLI
import presentation.features.clothes.GetWeeklyOutfitCLI
import presentation.features.location.GetCurrentLocationCLI
import presentation.features.location.GetIpAddressCLI
import presentation.features.weather.GetCurrentWeatherCLI
import presentation.features.weather.GetTomorrowWeatherCLI
import presentation.features.weather.GetWeeklyWeatherCLI

val cliModule = module {
    single<InputReader> { ConsoleInputReader() }
    single<OutputPrinter> { ConsoleOutputPrinter() }
    single { GetIpAddressCLI(get(), get(), get()) }
    single { GetCurrentLocationCLI(get(), get(), get()) }
    single { GetWeeklyOutfitCLI(get(), get(), get()) }
    single { GetOutfitCLI(get(), get(), get()) }
    single { GetTomorrowWeatherCLI(get(), get(), get()) }
    single { GetWeeklyWeatherCLI(get(), get(), get()) }
    single { GetCurrentWeatherCLI(get(), get(), get()) }
    single { ClothesSuggesterCLI(get(), get(), get(), get(), get(), get(),get(),get()) }
}