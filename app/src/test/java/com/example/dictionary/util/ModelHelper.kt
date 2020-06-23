package com.example.dictionary.util

import com.example.dictionary.model.Base
import com.example.dictionary.model.List

object ModelHelper {

    private fun createListItem() = List(
        "definition",
        "permalink",
        10,
        arrayListOf(),
        "author",
        "word",
        1,
        "current vote",
        "written on",
        "example",
        5
    )

    fun createBase(count: Int): Base {
        var terms = arrayListOf<List>()
        for(i in 0 until count) {
            terms.add(createListItem())
        }
        return Base(1, terms)
    }
}