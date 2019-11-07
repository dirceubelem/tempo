package br.com.cotemig.tempo.models

import com.google.gson.annotations.SerializedName

class ForecastItem {

    @SerializedName("dt")
    var date: String? = null

    @SerializedName("temp")
    var value: WeatherForecast? = null

}