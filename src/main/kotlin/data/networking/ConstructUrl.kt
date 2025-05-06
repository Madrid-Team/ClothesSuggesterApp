package data.networking

import data.utils.Constants.CLOTHES_BASE_URL
import data.utils.Constants.WEATHER_BASE_URL


fun constructUrl(url: String): String {
    return when {
        url.contains(CLOTHES_BASE_URL) -> url
        url.startsWith("/") -> CLOTHES_BASE_URL + url.drop(1)
        else -> CLOTHES_BASE_URL + url
    }
}