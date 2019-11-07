package br.com.cotemig.tempo.models

import com.google.gson.annotations.SerializedName

class Weather {

    @SerializedName("temp")
    var temperature: Double? = null

    var pressure: Double? = null
    var humidity: Double? = null

    @SerializedName("temp_min")
    var minimum: Double? = null

    @SerializedName("temp_max")
    var maximum: Double? = null

}