package com.example.dictionary.model

import io.reactivex.Single

interface DictionaryRepository {
    fun getTerm(term:String):Single<Base>
}