package me.test.jcdecaux.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.test.jcdecaux.data.network.RestApi
import me.test.jcdecaux.presentation.model.StationEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StationViewModel : ViewModel() {

    val stationListLiveData: MutableLiveData<List<StationEntity>> = MutableLiveData()
    val failure : MutableLiveData<Unit> = MutableLiveData()

    fun loadStation() {

        val baseURL = "https://api.jcdecaux.com/vls/v1/"
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseURL)
            .build()

        var service = retrofit.create(RestApi::class.java)


        viewModelScope.launch {

            val tanStops = service.getStations();

            tanStops.enqueue(object: Callback<List<StationEntity>> {
                override fun onResponse(call: Call<List<StationEntity>>, response: Response<List<StationEntity>>) {
                    val stations = response.body()
                    stationListLiveData.value = stations;
                }
                override fun onFailure(call: Call<List<StationEntity>>, t: Throwable) {
                    failure.value = Unit;
                }
            })

        }
    }

}