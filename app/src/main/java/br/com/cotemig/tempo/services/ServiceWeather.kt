package br.com.cotemig.tempo.services

import br.com.cotemig.tempo.models.Forecast
import br.com.cotemig.tempo.models.ResultWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceWeather {

    @GET("weather")
    fun get(@Query("q") query: String,
            @Query("appid") appId: String,
            @Query("units") units: String): Call<ResultWeather>

    @GET("forecast/daily")
    fun getForecast(@Query("id") id: String,
            @Query("appid") appId: String,
            @Query("units") units: String): Call<Forecast>

}