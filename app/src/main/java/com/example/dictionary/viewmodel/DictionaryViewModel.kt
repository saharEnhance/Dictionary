package com.example.dictionary.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.model.DictionaryRepositoryImp
import com.example.dictionary.model.List
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(private val dictionaryRepositoryImp: DictionaryRepositoryImp) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val stateMutableLiveData = MutableLiveData<AppState>()
    val stateLiveData: LiveData<AppState> get() = stateMutableLiveData
    var loaded = false

    fun getList(term:String) {
        stateMutableLiveData.value = AppState.LOADING
        //load()
        disposable.add(
            dictionaryRepositoryImp.getTerm(term).subscribe({
                loaded = true
                if (it == null) {
                    stateMutableLiveData.value = AppState.ERROR("No term Retrieved")
                } else {
                    stateMutableLiveData.value = AppState.SUCCESSFUL(it.list)
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
    private fun load() {
        // set correct visible element
      /*  progressBarVisibilityMutableLiveData.value = View.VISIBLE
        listVisibilityMutableLiveData.value = View.GONE
        errorVisibilityMutableLiveData.value = View.GONE*/
    }
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
    sealed class AppState {
        object LOADING : AppState()
        data class SUCCESSFUL(val termList: ArrayList<List>) : AppState()
        data class ERROR(val message: String) : AppState()
    }
}