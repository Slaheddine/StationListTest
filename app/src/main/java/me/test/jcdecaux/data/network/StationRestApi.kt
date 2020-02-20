package me.test.jcdecaux.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val THE_MOVIE_DATABASE = "https://api.jcdecaux.com/vls/v1/"

class StationRestApi constructor() {

    val service: RestApi

    init {
        service = createWebService<RestApi>(THE_MOVIE_DATABASE)
    }

    inline fun <reified T> createWebService(baseUrl: String): T {

        val httpClient = OkHttpClient.Builder()

        httpClient.interceptors().add(QueryInterceptor())

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(httpClient.build())
            .build()

        var service = retrofit.create(T::class.java)

        return  service;
    }

    class QueryInterceptor: Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val url = chain.request().url().newBuilder()
                .addQueryParameter("apiKey", "76cd97ebd015b42a65fe4bc333c7e64f0e8dad9c")
                .build()

            val request = chain.request().newBuilder()
                .url(url)
                .build()

            return chain.proceed(request)
        }
    }

}