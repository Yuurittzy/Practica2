package com.example.practica2_yuritzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import com.example.practica2_yuritzy.databinding.ActivityInsertBinding
import com.example.practica2_yuritzy.db.DBBoxes
import com.example.practica2_yuritzy.db.DBHelper

class InsertActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInsertBinding
    private var nameTmp = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSpinnerData()


    }
    private fun setSpinnerData() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        val values = listOf(getString(R.string.naruto),getString(R.string.sakura),getString(R.string.itachi))
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, values)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // showImage(position)
                nameTmp = values[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
    fun showImage(position: Int) {
     val image = findViewById<ImageView>(R.id.ivBox)
     val resource = when(position) {
         0 -> AppCompatResources.getDrawable(this, R.drawable.naruto)
         1 -> AppCompatResources.getDrawable(this, R.drawable.sakura)
         2 -> AppCompatResources.getDrawable(this, R.drawable.itachi)

         else -> AppCompatResources.getDrawable(this, R.drawable.naruto)
     }
     image.setImageDrawable(resource)
 }

fun click(view: android.view.View) {
    val dbBoxes = DBBoxes(this)

    with(binding){

        if(!tietPrice.text.toString().isEmpty() && !tietQuantity.text.toString().isEmpty()){
            val id = dbBoxes.insertGame(nameTmp, tietQuantity.text.toString(), tietPrice.text.toString())

            if(id > 0) { //el registro se insertó correctamente
                Toast.makeText(this@InsertActivity, "Registro guardado exitosamente", Toast.LENGTH_LONG).show()

                //Reiniciamos las cajas de texto
                tietQuantity.setText("")
                tietPrice.setText("")
                tietQuantity.requestFocus()
            }else{
                Toast.makeText(this@InsertActivity, "Error al guardar el registro", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this@InsertActivity, "Por favor llene todos los campos", Toast.LENGTH_LONG).show()

            //Para mandar un error en una caja de texto especíica
            //tietTitulo.text = "Por favor agrega un título"
        }

    }

}

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }




}