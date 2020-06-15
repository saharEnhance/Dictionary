package com.example.dictionary.view

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.model.Base


class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(hourly: Base, clickListener: (Base) -> Unit) {
       // val sdf = SimpleDateFormat("MM/dd/yy hh.mm aa")
 /*       val sdf = SimpleDateFormat("hh.mm aa")
        val netDate = Date(hourly.dt.toLong() * 1000)
        val date = sdf.format(netDate)
        Log.d("dateTag", date)

        itemView.timeView.text = date.toString()
        itemView.degreeView.text = hourly.temp.toString()
        itemView.percentView.text = hourly.wind_speed.toString()
        var st = hourly.weather[0].icon

        Picasso.get().load("https://openweathermap.org/img/wn/$st@2x.png")
            .into(itemView.conditonView);*/

        itemView.setOnClickListener { clickListener(hourly) }
    }
}