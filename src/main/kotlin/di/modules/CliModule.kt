package di.modules


import org.koin.dsl.module
import presentation.ClothesSuggesterCLI
import presentation.components.ConsoleInputReader
import presentation.components.ConsoleOutputPrinter
import presentation.components.InputReader
import presentation.components.OutputPrinter

val cliModule = module {
    single<InputReader> { ConsoleInputReader() }
    single<OutputPrinter> { ConsoleOutputPrinter() }
    single { ClothesSuggesterCLI(get(), get(), get(), get(), get(), get()) }
}