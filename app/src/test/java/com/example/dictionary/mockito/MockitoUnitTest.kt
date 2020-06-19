package com.example.dictionary.mockito

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dictionary.model.DictionaryRepository
import com.example.dictionary.model.DictionaryRepositoryImp
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import java.net.UnknownHostException

class MockitoUnitTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val repository = mock<DictionaryRepositoryImp>()

   //val viewmodel by lazy{DictionaryViewModel(repository)}
    @Before
    fun initTest() {
        Mockito.reset(repository)
    }
    @Test
    fun test(){
        whenever(repository.getTerm(ArgumentMatchers.anyString()))
            .thenReturn(Single.error(UnknownHostException()))
       // viewmodel.getList("above")

    }

}