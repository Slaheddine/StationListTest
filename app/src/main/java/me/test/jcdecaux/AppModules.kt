package me.test.jcdecaux

import me.test.jcdecaux.data.network.RestApi
import me.test.jcdecaux.presentation.viewmodels.StationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val THE_MOVIE_DATABASE = "https://api.jcdecaux.com/vls/v1/"

val appModules = module {

    single {
        createWebService<RestApi>(THE_MOVIE_DATABASE)
    }

    viewModel { StationViewModel() }

}


inline fun <reified T> createWebService(baseUrl: String): T {

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    var service = retrofit.create(T::class.java)

    return  service;
}
