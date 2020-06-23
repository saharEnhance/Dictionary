package com.example.dictionary.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dictionary.model.Base
import com.example.dictionary.model.DictionaryRepository
import com.example.dictionary.util.ModelHelper.createBase
import io.reactivex.Single
import org.junit.*

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.only
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class DictionaryViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: DictionaryRepository

    @InjectMocks
    lateinit var dictionaryViewModel:DictionaryViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getList() {
        Mockito.`when`(repository.getTerm("term")).thenReturn(getMockBase())
        dictionaryViewModel.getList("term")
        dictionaryViewModel.stateLiveData.observeForever {  }
        assertEquals(DictionaryViewModel.AppState.SUCCESSFUL(createBase(1).list), dictionaryViewModel.stateLiveData.value)
        Mockito.verify(repository, only()).getTerm("term")

    }

    private fun getMockBase(): Single<Base> {
        return Single.just(createBase(1))
    }

}