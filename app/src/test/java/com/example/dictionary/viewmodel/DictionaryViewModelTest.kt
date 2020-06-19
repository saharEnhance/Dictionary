package com.example.dictionary.viewmodel

import com.example.dictionary.mockito.mock
import com.example.dictionary.model.DictionaryRepository
import com.example.dictionary.model.DictionaryRepositoryImp
import org.junit.*

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DictionaryViewModelTest {

    val repository = mock<DictionaryRepository>()

//    val viewmodel by lazy{DictionaryViewModel(repository)}

    var times = 0
    var timesAfter =0

    @Before
    fun setUp() {
        times++
        println("Before $timesAfter")

    }

    @Test
    fun getList() {
        assertEquals(1, times)
    }

    @Test
    fun getListZAfter() {
        assertEquals(2, times)
    }

    @After
    fun tearDown() {
        timesAfter++
        println("After $timesAfter")
    }


    companion object {
        @AfterClass
        fun afterClass() {
            println("After class")
        }

        @BeforeClass
        fun initial() {
            println("BeforeClass")
        }
    }
}