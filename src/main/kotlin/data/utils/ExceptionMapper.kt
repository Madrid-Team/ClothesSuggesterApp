package data.utils

import domain.utils.exceptions.WeatherExceptions
import io.ktor.client.plugins.*
import kotlinx.io.IOException
import java.net.UnknownHostException

fun Throwable?.toWeatherException(): WeatherExceptions {
    return when (this) {
        null ->
            WeatherExceptions.WeatherApiFailedException()

        is UnknownHostException,
        is IOException ->
            WeatherExceptions.WeatherApiFailedException()

        is ClientRequestException -> when (response.status.value) {
            429 -> WeatherExceptions.WeatherApiRateLimitException()
            else -> WeatherExceptions.WeatherApiFailedException()
        }

        is kotlinx.serialization.SerializationException ->
            WeatherExceptions.WeatherConditionNotSupportedException()

        else -> WeatherExceptions.WeatherApiFailedException()
    }
}
