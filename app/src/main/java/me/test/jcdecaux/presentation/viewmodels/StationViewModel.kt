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
import org.koin.core.KoinComponent
import org.koin.core.inject

class StationViewModel : ViewModel(), KoinComponent {

    val stationListLiveData: MutableLiveData<List<StationEntity>> = MutableLiveData()
    val failure : MutableLiveData<Unit> = MutableLiveData()

    private val service by inject<RestApi>()

    fun loadStation() {

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