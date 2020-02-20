package me.test.jcdecaux.data.mapper

import me.test.jcdecaux.data.model.StationData
import me.test.jcdecaux.domain.model.Position
import me.test.jcdecaux.domain.model.Station


class StationDataMapper constructor() {

    fun transform(data: StationData?): Station? {
        if (data != null) {
            val station = Station(
                data.number,
                data.contract_name,
                data.name,
                data.address,
                Position(data.position!!.lat, data.position!!.lng),
                data.banking,
                data.bonus,
                data.bike_stands,
                data.available_bike_stands,
                data.available_bikes,
                data.status,
                data.last_update
            )

            return station
        }
        return null
    }


    fun transform(list : List<StationData>?) : List<Station> {
        val result : MutableList<Station> = ArrayList()
        if (list != null) {
            for (movieEntity : StationData in list) {
                var station = transform(movieEntity)
                if (station!=null) {
                    result.add(station)
                }
            }
        }
        return result
    }


}