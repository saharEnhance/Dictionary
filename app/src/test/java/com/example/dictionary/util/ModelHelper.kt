package com.example.dictionary.util

import com.example.dictionary.model.Base
import com.example.dictionary.model.DictionaryList

object ModelHelper {

    private fun createListItem() = DictionaryList(
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
        var terms = arrayListOf<DictionaryList>()
        for(i in 0 until count) {
            terms.add(createListItem())
        }
        return Base(1, terms)
    }
}