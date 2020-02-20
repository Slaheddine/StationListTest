package me.test.jcdecaux

import me.test.jcdecaux.data.network.RestApi
import me.test.jcdecaux.data.network.StationRestApi
import me.test.jcdecaux.presentation.viewmodels.StationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModules = module {

    single {
        StationRestApi()
    }

    viewModel { StationViewModel() }

}

