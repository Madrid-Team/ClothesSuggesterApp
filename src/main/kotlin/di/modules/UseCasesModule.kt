package di.modules


import domain.usecases.clothes.GetOutfitUseCase
import domain.usecases.clothes.GetWeeklyOutfitUseCase
import domain.usecases.weather.GetCurrentWeatherUseCase
import domain.usecases.weather.GetWeeklyWeatherUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { GetOutfitUseCase(get()) }
    single { GetWeeklyOutfitUseCase(get()) }
     single { GetWeeklyWeatherUseCase(get()) }
    single { GetCurrentWeatherUseCase(get()) }

}


