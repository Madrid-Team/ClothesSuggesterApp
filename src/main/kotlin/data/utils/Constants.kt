package data.utils

object Constants {
    const val WEATHER_BASE_URL = "https://api.open-meteo.com/v1/forecast"
    const val CLOTHES_BASE_URL = "https://asss-fa991-default-rtdb.firebaseio.com/"
    const val LOCATION_ADDRESS_BASE_URL = "https://ipapi.co/%s/json/"
    const val IP_ADDRESS_BASE_URL = "https://api.ipify.org?format=json"
    const val WEATHER_DAILY_PARAMETER = "weather_code,temperature_2m_max,temperature_2m_min"
    const val WEATHER_CURRENT_PARAMETER = "temperature_2m,relative_humidity_2m,is_day,rain,snowfall,showers,precipitation,wind_speed_10m,cloud_cover,weather_code"
}