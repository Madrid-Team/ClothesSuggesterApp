package di.modules


import domain.usecases.clothes.GetOutfitUseCase
import domain.usecases.clothes.GetWeeklyOutfitUseCase
import domain.usecases.location.GetCurrentLocationUseCase
import domain.usecases.weather.GetCurrentWeatherUseCase
import domain.usecases.weather.GetTomorrowWeatherUseCase
import domain.usecases.weather.GetWeeklyWeatherUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { GetOutfitUseCase() }
    single { GetWeeklyOutfitUseCase() }
    single { GetCurrentLocationUseCase() }
    single { GetCurrentWeatherUseCase(get()) }
    single { GetTomorrowWeatherUseCase(get()) }
    single { GetWeeklyWeatherUseCase() }

}


