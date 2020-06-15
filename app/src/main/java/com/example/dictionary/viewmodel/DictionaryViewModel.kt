package com.example.dictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.model.Base
import com.example.dictionary.model.DictionaryRepositoryImp
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(private val dictionaryRepositoryImp: DictionaryRepositoryImp) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val stateMutableLiveData = MutableLiveData<AppState>()
    val stateLiveData: LiveData<AppState> get() = stateMutableLiveData
    var loaded = false

    fun getList(term:String) {
        //stateMutableLiveData.value = AppState.LOADING
        disposable.add(
            dictionaryRepositoryImp.getTerm(term).subscribe({
                loaded = true
                if (it == null) {
                    stateMutableLiveData.value = AppState.ERROR("No term Retrieved")
                } else {
                    stateMutableLiveData.value = AppState.SUCCESSFUL(it)
                }
            }, {
                loaded = true
                //errors
                val errorString = when (it) {
                    is UnknownHostException -> it.localizedMessage
                    else -> it.localizedMessage
                }
                stateMutableLiveData.value = AppState.ERROR(errorString)
            })
        )
    }
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
    sealed class AppState {
        object LOADING : AppState()
        data class SUCCESSFUL(val termList: Base) : AppState()
        data class ERROR(val message: String) : AppState()
    }
}