package me.test.jcdecaux

import me.test.jcdecaux.data.mapper.StationDataMapper
import me.test.jcdecaux.data.network.StationRestApi
import me.test.jcdecaux.data.repository.StationsRepositoryImp
import me.test.jcdecaux.domain.mappper.StationMapper
import me.test.jcdecaux.domain.repository.StationsRepository
import me.test.jcdecaux.domain.usercases.GetStationsUseCase
import me.test.jcdecaux.presentation.viewmodels.StationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single {
        StationRestApi()
    }

    single {
        StationDataMapper()
    }

    single {
        StationMapper()
    }

    single {
        StationsRepositoryImp(get(), get())
    }


    single {
        GetStationsUseCase(get(), get())
    }

    viewModel { StationViewModel(get()) }

}

