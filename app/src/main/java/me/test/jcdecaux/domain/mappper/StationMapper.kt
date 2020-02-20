package me.test.jcdecaux.domain.mappper

import me.test.jcdecaux.domain.model.Station
import me.test.jcdecaux.presentation.model.PositionItem
import me.test.jcdecaux.presentation.model.StationItem


class StationMapper constructor() {

    fun transform(data: Station?): StationItem? {
        if (data != null) {
            val station = StationItem(
                data.number,
                data.contract_name,
                data.name,
                data.address,
                PositionItem(data.position!!.lat, data.position!!.lng),
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


    fun transform(list : List<Station>?) : List<StationItem> {
        val result : MutableList<StationItem> = ArrayList()
        if (list != null) {
            for (movieEntity : Station in list) {
                var station = transform(movieEntity)
                if (station!=null) {
                    result.add(station)
                }
            }
        }
        return result
    }


}