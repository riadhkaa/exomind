package com.example.exomindtest.Weather.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exomindtest.R
import com.example.exomindtest.Weather.data.model.WeatherData
import com.example.exomindtest.Weather.ui.viewmodel.WeatherViewModel


class WeatherAdapter(val viewModel:WeatherViewModel , val myDataset: ArrayList<WeatherData>, context: Context) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>(){


    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {

        var region : TextView? = null
        var temperature : TextView? = null
        var clouds : TextView? = null

        init {
            this.region = row.findViewById(R.id.ville)
            this.temperature = row.findViewById(R.id.temperature)
            this.clouds = row.findViewById(R.id.clouds)
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val weather = myDataset?.get(position)
        holder.region?.text = weather.region
        holder.clouds?.text = weather.cloud.cloud
        holder.temperature?.text = weather.main.temperature

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.weather_details_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }



}