package presentation

import di.modules.cliModule
import di.modules.dataSourceModule
import di.modules.networkModule
import di.modules.repositoryModule
import di.modules.useCasesModule
import org.koin.core.context.GlobalContext.startKoin

fun main() {
    startKoin { modules(dataSourceModule,networkModule, repositoryModule, useCasesModule, cliModule) }
}