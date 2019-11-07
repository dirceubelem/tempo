package br.com.cotemig.tempo.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.cotemig.tempo.R
import br.com.cotemig.tempo.helpers.DateTime
import br.com.cotemig.tempo.models.Forecast
import br.com.cotemig.tempo.models.Location
import br.com.cotemig.tempo.models.ResultWeather
import br.com.cotemig.tempo.models.Weather
import br.com.cotemig.tempo.services.RetrofitInitializer
import br.com.cotemig.tempo.ui.adapters.ForecastAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this)
            .load(R.drawable.map)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 15)))
            .into(background)

        getLocation()

    }

    fun getLocation() {
        var s = RetrofitInitializer().serviceLocation()
        var call = s.get()

        call.enqueue(object : retrofit2.Callback<Location> {

            override fun onResponse(call: Call<Location>?, response: Response<Location>?) {
                response?.let {

                    if (it.code() == 200) {
                        city.text = it.body().city
                        city2.text = it.body().city

                        getWeather(it.body().city)
                    }

                }
            }

            override fun onFailure(call: Call<Location>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }

    fun getWeather(city: String) {

        var s = RetrofitInitializer().serviceWeather()

        var call = s.get(city, "7489ef6eb644acdd47a1ce5776d531bd", "metric")

        call.enqueue(object : retrofit2.Callback<ResultWeather> {

            override fun onResponse(call: Call<ResultWeather>?, response: Response<ResultWeather>?) {

                response?.let {

                    if (it.code() == 200) {

                        it.body().data?.let {
                            temp.text = String.format("%.0f", it.temperature).plus("°")
                        }

                        getForecast(it.body())
                        //var datetime = DateTime(it.body().datetime!! * 1000)

                    }

                }

            }

            override fun onFailure(call: Call<ResultWeather>?, t: Throwable?) {

            }

        })


    }

    fun getForecast(w: ResultWeather) {

        var s = RetrofitInitializer().serviceWeather()

        var call = s.getForecast(w.city!!, "7489ef6eb644acdd47a1ce5776d531bd", "metric")

        call.enqueue(object : retrofit2.Callback<Forecast> {

            override fun onResponse(call: Call<Forecast>?, response: Response<Forecast>?) {
                response?.let {
                    if (it.code() == 200) {

                        list.adapter = ForecastAdapter(this@MainActivity, it.body().list)
                        list.layoutManager = LinearLayoutManager(this@MainActivity,
                            LinearLayoutManager.HORIZONTAL, false)

                    }
                }
            }

            override fun onFailure(call: Call<Forecast>?, t: Throwable?) {

            }
        })

    }
}
