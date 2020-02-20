package me.test.jcdecaux.data.model


data class StationData (
    val number: Int,
    val contract_name: String?,
    val name: String?,
    val address: String?,
    val position: PositionData?,
    val banking: Boolean,
    val bonus: Boolean,
    val bike_stands: Int,
    val available_bike_stands: Int,
    val available_bikes: Int,
    val status: String?,
    val last_update: Double
)