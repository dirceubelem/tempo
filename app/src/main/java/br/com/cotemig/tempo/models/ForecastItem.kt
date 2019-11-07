package br.com.cotemig.tempo.models

import com.google.gson.annotations.SerializedName

class ForecastItem {

    @SerializedName("dt")
    var date: Long? = null

    @SerializedName("temp")
    var value: WeatherForecast? = null

}