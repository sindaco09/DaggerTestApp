package com.indaco.daggertestapp.ui.screens.main.bart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.indaco.daggertestapp.data.model.bart.BartStation
import com.indaco.daggertestapp.databinding.RowItemBartObjectBinding

class BartStationAdapter (
    private val list: List<BartStation>
) : RecyclerView.Adapter<BartStationAdapter.ViewHolder>() {

    class ViewHolder(val binding: RowItemBartObjectBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowItemBartObjectBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = list[position]

        holder.binding.text.text = station.name
    }

    override fun getItemCount() = list.size
}