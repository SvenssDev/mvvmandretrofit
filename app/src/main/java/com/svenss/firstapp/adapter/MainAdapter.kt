package com.svenss.firstapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.svenss.firstapp.R
import com.svenss.firstapp.model.Country
import com.svenss.firstapp.util.loadImage
import kotlinx.android.synthetic.main.adapter_main.view.*

/**
 * Created by miguelleon on 12,octubre,2021
 */

class MainAdapter(private val listCountries: MutableList<Country>): RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false))
    }

    override fun getItemCount() = listCountries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems()
    }

    /** View Holder is the class for inflate Views and show in screen */
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val ivCountry = view.iv_adapter_main
        private val tvCountry = view.tv_adapter_main

        fun bindItems(){
            val urlImage = listCountries[adapterPosition].country_image

            ivCountry?.loadImage(urlImage)
            tvCountry.text = listCountries[adapterPosition].country_name
        }
    }
}