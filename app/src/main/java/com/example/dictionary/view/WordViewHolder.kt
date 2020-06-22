package com.example.dictionary.view

import android.view.View
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.model.List
import kotlinx.android.synthetic.main.item.view.*


class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(list: List, clickListener: (List) -> Unit) {

        itemView.defView.text = list.definition
        itemView.thumbsDownView.text ="thumbs_down: " + list.thumbs_down.toString()
        itemView.thumbsUPView.text ="thumbs_up: " + list.thumbs_up.toString()
        itemView.setOnClickListener { clickListener(list) }
    }
}