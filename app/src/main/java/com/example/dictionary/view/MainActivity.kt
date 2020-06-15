package com.example.dictionary.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.example.dictionary.R
import com.example.dictionary.inject.DictionaryApplication
import com.example.dictionary.model.Base
import com.example.dictionary.viewmodel.DictionaryViewModel
import com.example.dictionary.viewmodel.DictionaryViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: DictionaryViewModelFactory
    lateinit var viewModel: DictionaryViewModel

   // var adapter: WordAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as DictionaryApplication).appComponent.inject(this)
        viewModel = viewModelFactory.create(DictionaryViewModel::class.java)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = " "

        Log.d("TAGB", "begin")
        //viewModel.getList("peek")

        viewModel.stateLiveData.observe(this, Observer { appState ->

            when (appState) {
                is DictionaryViewModel.AppState.LOADING -> displayToast("Loading")
                is DictionaryViewModel.AppState.SUCCESSFUL -> {
                    Log.d("TAG1", "here1")
                    displayList(appState.termList)
                }
                is DictionaryViewModel.AppState.ERROR -> displayToast("error!!")
                else -> displayToast("Something Went Wrong... Try Again.")
            }
        })
        viewModel.getList("peek")
        // adapter?.updateWeather(hourly)
        // initRecyclerView(hourly)
        Log.d("TAG20", "here20")
    }

    private fun displayList(list: Base) {
        Log.d("TAG2", "here2")
        Log.d("TAGdic", list.toString())

    }
/*
    private fun displayWeather(weatherList: Base, hourly: ArrayList<Hourly>) {

        // adapter?.updateWeather(hourly)
        // initRecyclerView(hourly)
        textTemp.text = weatherList.current.temp.toString()
        textFeel.text = weatherList.current.feels_like.toString()
        textDescription.text = weatherList.current.weather[0].description
        var st = weatherList.current.weather[0].icon
        Picasso.get().load("https://openweathermap.org/img/wn/$st@2x.png").into(imageView)
        minMax.text = weatherList.current.humidity.toString()
        val sdf = SimpleDateFormat("dd/MM/yy hh:mm a")
        val netDate = Date(weatherList.current.dt.toLong() * 1000)
        val date = sdf.format(netDate)
        dateText.text = date
        minMax.text = weatherList.current.humidity.toString()
    }

    private fun initRecyclerView(hourly: ArrayList<Hourly>) {

        HourlyForecast.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter = HourlyAdapter(hourly) { hourly: Hourly ->
            onHourlySelected(hourly)
        }
        HourlyForecast.adapter = adapter
    }

    private fun onHourlySelected(hourly: Hourly) {
        Toast.makeText(this, "Clicked: $hourly", Toast.LENGTH_LONG).show()
    }*/

    private fun partItemClicked(word: String) {
        Toast.makeText(this, "Clicked: $word", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    displayToast("toasteeed")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
        displayToast(searchItem.toString())
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.option_celcius -> {
                displayToast(getString(R.string.option_celcius_message))
                return true
            }
            R.id.option_farenheit -> {
                displayToast(getString(R.string.option_farenheit_message))
                return true
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun displayToast(message: String?) {
        Toast.makeText(
            applicationContext, message,
            Toast.LENGTH_SHORT
        ).show()
    }
}

