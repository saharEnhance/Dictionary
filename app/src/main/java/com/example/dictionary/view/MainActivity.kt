package com.example.dictionary.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.R
import com.example.dictionary.inject.DictionaryApplication
import com.example.dictionary.model.List
import com.example.dictionary.viewmodel.DictionaryViewModel
import com.example.dictionary.viewmodel.DictionaryViewModelFactory
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: DictionaryViewModelFactory
    lateinit var viewModel: DictionaryViewModel

    var adapter: WordAdapter? = null
    lateinit var list: ArrayList<List>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as DictionaryApplication).appComponent.inject(this)
        viewModel = viewModelFactory.create(DictionaryViewModel::class.java)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = " "

        val bt: Button = findViewById(R.id.searchBtn)
        val tx = findViewById<EditText>(R.id.searchText)

       // val progressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar

        viewModel.stateLiveData.observe(this, Observer { appState ->

            when (appState) {
                is DictionaryViewModel.AppState.LOADING -> displayToast("loading!")
                is DictionaryViewModel.AppState.SUCCESSFUL -> {
                    list = appState.termList
                    displayList()
                }
                is DictionaryViewModel.AppState.ERROR -> displayToast("error!!")
                else -> displayToast("Something Went Wrong... Try Again.")
            }
        })
        bt.setOnClickListener {
            viewModel.getList(tx.text.toString())
        }
    }

    private fun displayList() {

        println("===" + list.toString())
        adapter?.updateDictionary(false)
        initRecyclerView(list)
    }

    private fun initRecyclerView(word: ArrayList<List>) {

        dictionaryRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = WordAdapter(word) { word: List ->
            onHourlySelected(word)
        }
        dictionaryRecycler.adapter = adapter
    }

    private fun onHourlySelected(word: List) {
        Toast.makeText(this, "Clicked: $word", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.sort_thumbsUp -> {
                adapter?.updateDictionary(false)
                return true
            }
            R.id.sort_thumbsDown -> {
                adapter?.updateDictionary(true)
                return true
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayToast(message: String?) {
        Toast.makeText(
            applicationContext, message,
            Toast.LENGTH_SHORT
        ).show()
    }

/*    private fun progressNow() {

        progressBar.visibility = View.VISIBLE
    }

    private fun progressStop() {

        progressBar.visibility = View.GONE
    }*/
}