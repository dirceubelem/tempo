package br.com.cotemig.tempo.models

import com.google.gson.annotations.SerializedName

class ResultWeather {

    @SerializedName("main")
    var data: Weather? = null

    @SerializedName("dt")
    var datetime: Long? = null

    @SerializedName("id")
    var city: Long? = null

}