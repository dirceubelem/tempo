package br.com.cotemig.tempo.models

import com.google.gson.annotations.SerializedName

class WeatherForecast  {

    @SerializedName("day")
    var temperature: Double? = null

    @SerializedName("min")
    var minimum: Double? = null

    @SerializedName("max")
    var maximum: Double? = null

}