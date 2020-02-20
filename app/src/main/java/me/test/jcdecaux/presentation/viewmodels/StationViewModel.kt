package me.test.jcdecaux.presentation.viewmodels

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import me.test.jcdecaux.data.network.StationRestApi
import me.test.jcdecaux.data.repository.StationsRepositoryImp
import me.test.jcdecaux.domain.mappper.StationMapper
import me.test.jcdecaux.domain.model.Station
import me.test.jcdecaux.presentation.model.StationItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.koin.core.KoinComponent
import org.koin.core.inject

class StationViewModel : ViewModel(), KoinComponent {

    val stationListLiveData: MutableLiveData<List<StationItem>> = MutableLiveData()
    val failure : MutableLiveData<Unit> = MutableLiveData()

    private val stationsRepository by inject<StationsRepositoryImp>()
    private val mapper by inject<StationMapper>()

    fun loadStation() {

        val observer = stationsRepository.getStations();

        val subscribe = observer.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                stationListLiveData.value = mapper.transform(it)
            }, { error ->
                failure.value = Unit;
            })
    }

}