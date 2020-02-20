package me.test.jcdecaux.data.network

import me.test.jcdecaux.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class StationRestApi constructor() {

    val service: RestApi

    init {
        service = createWebService<RestApi>(BuildConfig.BASE_URL)
    }

    inline fun <reified T> createWebService(baseUrl: String): T {

        val httpClient = OkHttpClient.Builder()

        httpClient.interceptors().add(QueryInterceptor())

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(httpClient.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        var service = retrofit.create(T::class.java)

        return  service;
    }

    class QueryInterceptor: Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val url = chain.request().url().newBuilder()
                .addQueryParameter("apiKey", BuildConfig.API_KEY)
                .build()

            val request = chain.request().newBuilder()
                .url(url)
                .build()

            return chain.proceed(request)
        }
    }

}