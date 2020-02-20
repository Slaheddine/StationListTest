package me.test.jcdecaux.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.test.jcdecaux.domain.usercases.GetStationsUseCase
import me.test.jcdecaux.domain.usercases.UseCaseCallBack
import me.test.jcdecaux.presentation.model.StationItem


class StationViewModel(var getStationUseCase : GetStationsUseCase) : ViewModel() {

    val stationListLiveData: MutableLiveData<List<StationItem>> = MutableLiveData()
    val failure : MutableLiveData<Unit> = MutableLiveData()


    fun loadStation() {

        val observer = getStationUseCase.invoke( object: UseCaseCallBack<List<StationItem>> {
            override fun onSuccess(it: List<StationItem>) {
                stationListLiveData.value = it
            }

            override fun onFailure(error: Throwable) {
                failure.value = Unit;
            }
        })
    }

}