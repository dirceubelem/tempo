package br.com.cotemig.tempo.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {

    companion object {
        private val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().also { it -> it.level = HttpLoggingInterceptor.Level.BODY })
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        }

    }

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofit2 = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://ip-api.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun serviceWeather(): ServiceWeather {
        return retrofit.create(ServiceWeather::class.java)
    }

    fun serviceLocation(): ServiceLocation {
        return retrofit2.create(ServiceLocation::class.java)
    }

}