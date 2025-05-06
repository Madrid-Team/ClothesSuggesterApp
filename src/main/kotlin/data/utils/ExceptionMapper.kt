package data.utils

import domain.utils.exceptions.ClothesExceptions
import domain.utils.exceptions.LocationExceptions
import domain.utils.exceptions.WeatherExceptions
import io.ktor.client.plugins.*
import kotlinx.io.IOException
import java.net.UnknownHostException

fun Throwable.toWeatherException(): WeatherExceptions {
    return when (val exceptions = this) {

        is WeatherExceptions -> exceptions

        is UnknownHostException,
        is IOException ->
            WeatherExceptions.WeatherApiFailedException()


        is ClientRequestException -> when (response.status.value) {
            // Too Many Requests
            429 -> WeatherExceptions.WeatherApiRateLimitException()
            else -> WeatherExceptions.WeatherApiFailedException()
        }

        is kotlinx.serialization.SerializationException ->
            WeatherExceptions.WeatherConditionNotSupportedException()

        else -> WeatherExceptions.WeatherApiFailedException()
    }
}

fun Throwable.toClothesExceptions(): ClothesExceptions {
    return when (val exceptions = this) {
        is ClothesExceptions -> exceptions

        is kotlinx.serialization.SerializationException ->
            ClothesExceptions.MissingClothingDataException()

        is IllegalArgumentException -> ClothesExceptions.UnsupportedTemperatureRangeException()

        else -> ClothesExceptions.OutfitNotFoundException()
    }
}

fun Throwable.toLocationExceptions(): LocationExceptions {
    return when (val exceptions = this) {
        is LocationExceptions -> exceptions

        is UnknownHostException,
        is IOException ->
            LocationExceptions.LocationNotFoundException()

        is ClientRequestException -> when (response.status.value) {
            // Bad Request
            400 -> LocationExceptions.InvalidIpAddressException()
            else -> LocationExceptions.LocationNotFoundException()
        }

        else -> LocationExceptions.LocationNotFoundException()
    }
}
