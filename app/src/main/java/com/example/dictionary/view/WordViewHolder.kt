package com.example.dictionary.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.model.DictionaryList
import kotlinx.android.synthetic.main.item.view.*


class WordViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(list: DictionaryList, clickListener: (DictionaryList) -> Unit) {
        itemView.defView.text = list.definition

        itemView.thumbsDownView.text =
            view.context.getString(R.string.thumbs_downn, list.thumbs_down.toString())

        itemView.thumbsUPView.text =
            view.context.getString(R.string.thumbs_upp, list.thumbs_up.toString())

        itemView.setOnClickListener { clickListener(list) }
    }
}