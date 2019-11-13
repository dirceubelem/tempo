package br.com.cotemig.tempo.ui.adapters

import android.content.Context
import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import br.com.cotemig.tempo.R
import br.com.cotemig.tempo.helpers.DateTime
import br.com.cotemig.tempo.models.ForecastItem

class ForecastAdapter(var context: Context, var list: List<ForecastItem>?) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolderForecast>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderForecast {
        val view = LayoutInflater.from(context).inflate(R.layout.item_forecast, parent, false)
        return ViewHolderForecast(view)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolderForecast, position: Int) {
        holder.bind(context, list!![position])
    }

    class ViewHolderForecast(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(context: Context, item: ForecastItem){

            var temperature = itemView.findViewById<TextView>(R.id.temperature)
            temperature.text = String.format("%.0f", item.value!!.temperature).plus("°")
            var weekday = itemView.findViewById<TextView>(R.id.weekday)

            var date = DateTime(item.date!! * 1000)

            weekday.text = date.toString("EE")
            var minimum = itemView.findViewById<TextView>(R.id.minimum)
            minimum.text = String.format("%.0f", item.value!!.minimum).plus("°")
            var maximum = itemView.findViewById<TextView>(R.id.maximum)
            maximum.text = String.format("%.0f", item.value!!.maximum).plus("°")

            var graph = itemView.findViewById<RelativeLayout>(R.id.graph)
            graph.layoutParams.height = (item.value!!.temperature!!.toInt()).px

        }

        // criando uma extensão da classe Int
        val Int.dp: Int
            get() = (this / Resources.getSystem().displayMetrics.density).toInt()
        val Int.px: Int
            get() = (this * Resources.getSystem().displayMetrics.density).toInt()
    }

}