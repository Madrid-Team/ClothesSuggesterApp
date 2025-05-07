package data.utils

import domain.utils.exceptions.ClothesException
import domain.utils.exceptions.LocationException
import domain.utils.exceptions.WeatherException

fun Throwable.toWeatherException(): WeatherException {
    return when (val exceptions = this) {

        is WeatherException -> exceptions

        is NetworkException.SerializationException ->
            WeatherException.WeatherDataException(exceptions.message.toString())

        is NetworkException.RequestTimeoutException,
        is NetworkException.TooManyRequestsException
            -> WeatherException.WeatherDataException(exceptions.message.toString())

        is NetworkException.ServerErrorException -> WeatherException.WeatherDataException(exceptions.message.toString())

        is NetworkException.UnknownNetworkException -> WeatherException.WeatherDataException(exceptions.message.toString())

        is NetworkException.NoInternetConnectionException -> WeatherException.WeatherDataException(exceptions.message.toString())

        else -> WeatherException.WeatherDataException(exceptions.message.toString())
    }
}

fun Throwable.toClothesExceptions(): ClothesException {
    return when (val exceptions = this) {
        is ClothesException -> exceptions

        is NetworkException.SerializationException ->
            ClothesException.ClothingDataException(exceptions.message.toString())

        is NetworkException.RequestTimeoutException,
        is NetworkException.TooManyRequestsException
            -> ClothesException.ClothingDataException(exceptions.message.toString())

        is NetworkException.ServerErrorException -> ClothesException.ClothingDataException(exceptions.message.toString())

        is NetworkException.UnknownNetworkException -> ClothesException.ClothingDataException(exceptions.message.toString())

        is NetworkException.NoInternetConnectionException -> ClothesException.ClothingDataException(exceptions.message.toString())

        else -> ClothesException.ClothingDataException(exceptions.message.toString())
    }
}

fun Throwable.toLocationExceptions(): LocationException {
    return when (val exceptions = this) {
        is LocationException -> exceptions

        is NetworkException.SerializationException ->
            LocationException.LocationDataException(exceptions.message.toString())

        is NetworkException.RequestTimeoutException,
        is NetworkException.TooManyRequestsException
            -> LocationException.LocationDataException(exceptions.message.toString())

        is NetworkException.ServerErrorException -> LocationException.LocationDataException(exceptions.message.toString())

        is NetworkException.UnknownNetworkException -> LocationException.LocationDataException(exceptions.message.toString())

        is NetworkException.NoInternetConnectionException -> LocationException.LocationDataException(exceptions.message.toString())

        else -> LocationException.LocationDataException(exceptions.message.toString())
    }
}
