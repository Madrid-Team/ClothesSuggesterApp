package data.source.remote.responseModels

data class Daily(
    val temperatureMax: List<Double>,
    val temperatureMin: List<Double>,
    val time: List<String>,
    val weatherCode: List<Int>
)