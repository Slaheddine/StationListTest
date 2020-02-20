package me.test.jcdecaux.data.network


import me.test.jcdecaux.presentation.model.StationEntity
import retrofit2.Call
import retrofit2.http.GET

interface RestApi {

    @GET("stations?apiKey=76cd97ebd015b42a65fe4bc333c7e64f0e8dad9c")
    fun getStations(): Call<List<StationEntity>>
}