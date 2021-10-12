package com.svenss.firstapp.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.svenss.firstapp.R
import com.svenss.firstapp.adapter.MainAdapter
import com.svenss.firstapp.model.Country
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by miguelleon on 12,octubre,2021
 */

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initViews()
        observeViewModel()
    }

    private fun initViews(){
        showStateProgress(true)
        viewModel.startServiceCountries()
    }

    /** Observe view model and show data in interface and show to user */
    private fun observeViewModel(){
        viewModel.listCountries.observe(this, { list ->
            list?.let {
                showStateProgress(false)
                initRecycler(it)
            }
        })
    }

    private fun showStateProgress(state: Boolean){
        pb_countries_main_activity?.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun initRecycler(list: MutableList<Country>){
        rv_countries_main_activity?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MainAdapter(list)
        }
    }
}