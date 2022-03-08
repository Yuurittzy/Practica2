package com.example.practica2_yuritzy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.practica2_yuritzy.R
import com.example.practica2_yuritzy.databinding.ItemBoxBinding
import com.example.practica2_yuritzy.model.Box

class BoxesAdapter(context: Context, listGames: ArrayList<Box>): BaseAdapter() {
    private val listGames = listGames
    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return listGames.size
    }

    override fun getItem(position: Int): Any {
        return listGames[position]
    }

    override fun getItemId(position: Int): Long {
        return listGames[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemBoxBinding.inflate(layoutInflater)

        with(binding){
            tvNameBox.text = layoutInflater.context?.getString(R.string.name, listGames[position].name)
            tvQuantity.text = layoutInflater.context?.getString(R.string.quantity_total, listGames[position].quantity)
            tvPrice.text = layoutInflater.context?.getString(R.string.unit_price, listGames[position].price)
        }
        return binding.root
    }
}