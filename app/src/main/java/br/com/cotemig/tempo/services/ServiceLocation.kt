package br.com.cotemig.tempo.services

import br.com.cotemig.tempo.models.Location
import retrofit2.Call
import retrofit2.http.GET

interface ServiceLocation {

    @GET("json")
    fun get(): Call<Location>

}