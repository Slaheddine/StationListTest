package me.test.jcdecaux.data.repository

import io.reactivex.Observable
import me.test.jcdecaux.data.mapper.StationDataMapper
import me.test.jcdecaux.data.network.StationRestApi
import me.test.jcdecaux.domain.model.Station
import me.test.jcdecaux.domain.repository.StationsRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class StationsRepositoryImp : StationsRepository, KoinComponent {

    val stationRestApi by inject<StationRestApi>()
    val mapper by inject<StationDataMapper>()


    override fun getStations(): Observable<List<Station>> {
        return stationRestApi.service.getStations().map { mapper.transform(it) }
    }

}
