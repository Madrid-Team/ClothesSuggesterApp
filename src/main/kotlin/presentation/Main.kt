package presentation

import di.modules.cliModule
import di.modules.dataSourceModule
import di.modules.networkModule
import di.modules.repositoryModule
import di.modules.useCasesModule
import org.koin.core.context.GlobalContext.startKoin
import org.koin.java.KoinJavaComponent.getKoin

suspend fun main() {
    startKoin { modules(dataSourceModule,networkModule, repositoryModule, useCasesModule, cliModule) }
    val clothesSuggesterCLI: ClothesSuggesterCLI= getKoin().get()
    clothesSuggesterCLI.start()
}