package me.test.jcdecaux.domain.repository

import io.reactivex.Observable
import me.test.jcdecaux.domain.model.Station


interface StationsRepository {
    fun getStations() : Observable<List<Station>>
}
