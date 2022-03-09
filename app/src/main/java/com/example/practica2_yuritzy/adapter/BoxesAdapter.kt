package com.example.practica2_yuritzy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.example.practica2_yuritzy.R
import com.example.practica2_yuritzy.databinding.ItemBoxBinding
import com.example.practica2_yuritzy.model.Box

class BoxesAdapter(context: Context, private val boxes: ArrayList<Box>): BaseAdapter() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return boxes.size
    }

    override fun getItem(position: Int): Any {
        return boxes[position]
    }

    override fun getItemId(position: Int): Long {
        return boxes[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemBoxBinding.inflate(layoutInflater)

        with(binding){
            showImage(boxes[position].name, ivBox)
            tvNameBox.text = layoutInflater.context?.getString(R.string.name, boxes[position].name)
            tvQuantity.text = layoutInflater.context?.getString(R.string.quantity_total, boxes[position].quantity)
            tvPrice.text = layoutInflater.context?.getString(R.string.unit_price, boxes[position].price)

        }
        return binding.root
    }

    private fun showImage(name: String, imageView: ImageView) {
        val context = imageView.context
        val resource = when(name) {
            "Naruto" -> AppCompatResources.getDrawable(context, R.drawable.naruto)
            "Sakura" -> AppCompatResources.getDrawable(context, R.drawable.sakura)
            "Itachi" -> AppCompatResources.getDrawable(context, R.drawable.itachi)

            else -> AppCompatResources.getDrawable(context, R.drawable.naruto)
        }
        imageView.setImageDrawable(resource)
    }
}