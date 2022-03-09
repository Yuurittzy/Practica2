package com.example.practica2_yuritzy

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.practica2_yuritzy.adapter.BoxesAdapter
import com.example.practica2_yuritzy.databinding.ActivityMainBinding
import com.example.practica2_yuritzy.db.DBBoxes
import com.example.practica2_yuritzy.model.Box

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listGames: ArrayList<Box>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbGames = DBBoxes(this)
        listGames = dbGames.getBoxes()

        val gamesAdapter = BoxesAdapter(this, listGames)

        binding.lvBoxes.adapter = gamesAdapter

        binding.lvBoxes.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("ID", l.toInt())
            startActivity(intent)
        }

    }

    fun click(view: View) {
        startActivity(Intent(this, InsertActivity::class.java))
        finish()
    }

}