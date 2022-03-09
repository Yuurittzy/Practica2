package com.example.practica2_yuritzy

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.practica2_yuritzy.databinding.ActivityDetailsBinding
import com.example.practica2_yuritzy.db.DBBoxes
import com.example.practica2_yuritzy.model.Box

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var dbGames: DBBoxes
    private var nameTmp = ""
    var game: Box? = null
    var id:Int = 0

    val values by lazy { listOf(getString(R.string.naruto),getString(R.string.sakura),getString(R.string.itachi))}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSpinnerData()
        if(savedInstanceState == null){
            val bundle = intent.extras
            if(bundle != null){
                id = bundle.getInt("ID", 0)
            }
        }else{
            id = savedInstanceState.getSerializable("ID") as Int
        }

        dbGames = DBBoxes(this)
        game = dbGames.getBox(id)

        if(game != null){
            with(binding){
                spinner.setSelection(values.indexOf(game?.name))
                tietQuantity.setText(game?.quantity)
                tietPrice.setText(game?.price)

                tietQuantity.inputType = InputType.TYPE_NULL
                tietPrice.inputType = InputType.TYPE_NULL

            }
        }


    }

    private fun setSpinnerData() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, values)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                nameTmp = values[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }


    fun click(view: View) {
        when(view.id){
            R.id.btnEdit -> {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("ID", id)
                startActivity(intent)
                finish()
            }

            R.id.btnDelete -> {
                AlertDialog.Builder(this)
                    .setTitle("Confirmación")
                    .setMessage("¿Realmente deseas eliminar la cajita?")
                    .setPositiveButton("Sí") { _, _ ->
                        if (dbGames.deleteBox(id)) {
                            Toast.makeText(
                                this,
                                "Registro eliminado exitosamente",
                                Toast.LENGTH_LONG
                            ).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    }
                    .setNegativeButton("No") { _, _ -> }
                    .show()
            }
        }



    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}