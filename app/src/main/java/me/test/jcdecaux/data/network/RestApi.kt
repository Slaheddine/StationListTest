package me.test.jcdecaux.data.network


import io.reactivex.Observable
import me.test.jcdecaux.data.model.StationData
import retrofit2.http.GET

interface RestApi {

    @GET("stations")
    fun getStations(): Observable<List<StationData>>
}