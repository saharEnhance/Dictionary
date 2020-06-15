package com.example.dictionary.model

import com.example.dictionary.network.DictionaryRestService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DictionaryRepositoryImp @Inject constructor(
    private val dictionaryRestService: DictionaryRestService,
    private val database: DictionaryRoomDB
) : DictionaryRepository {
    override fun getTerm(term: String):Single<Base> {
        return dictionaryRestService.getTerm(term).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}