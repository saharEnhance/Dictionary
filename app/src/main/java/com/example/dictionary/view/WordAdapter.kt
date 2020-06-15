package com.example.dictionary.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.model.Base


class WordAdapter(private var list : ArrayList<Base>, private val clickListener: (Base) -> Unit):RecyclerView.Adapter<WordViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {

            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item, parent, false)
            return WordViewHolder(view)
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    fun updateWeather(weatherList:ArrayList<Base>){
        this.list.clear()
        this.list.addAll(weatherList)
        notifyDataSetChanged()

    }

}