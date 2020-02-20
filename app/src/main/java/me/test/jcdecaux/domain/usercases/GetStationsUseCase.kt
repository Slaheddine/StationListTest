package me.test.jcdecaux.domain.usercases

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.test.jcdecaux.data.repository.StationsRepositoryImp
import me.test.jcdecaux.domain.mappper.StationMapper
import me.test.jcdecaux.presentation.model.StationItem


open class GetStationsUseCase(private val stationRepo: StationsRepositoryImp, private val mapper : StationMapper) {

    fun invoke(callback : UseCaseCallBack<List<StationItem>>)  {

        var observer = stationRepo.getStations()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                callback.onSuccess(mapper.transform(it))
            }, { error ->
                callback.onFailure(error)
            })
    }
}