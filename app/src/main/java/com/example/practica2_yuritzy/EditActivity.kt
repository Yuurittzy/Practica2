package com.example.practica2_yuritzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.practica2_yuritzy.databinding.ActivityEditBinding
import com.example.practica2_yuritzy.db.DBBoxes
import com.example.practica2_yuritzy.model.Box

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    private lateinit var dbBoxes: DBBoxes
    var box: Box? = null
    var id: Int = 0
    val values by lazy { listOf(getString(R.string.naruto),getString(R.string.sakura),getString(R.string.itachi))}
    private var nameTmp = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
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

        dbBoxes = DBBoxes(this)

        box = dbBoxes.getBox(id)

        if(box != null){
            with(binding){
                spinner.setSelection(values.indexOf(box?.name))
                tietQuantity.setText(box?.quantity)
                tietPrice.setText(box?.price)
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
        with(binding){
            if(!tietPrice.text.toString().isEmpty() && !tietQuantity.text.toString().isEmpty()){
                if(dbBoxes.updateBox(id, nameTmp, tietQuantity.text.toString(), tietPrice.text.toString())){
                    Toast.makeText(this@EditActivity, "Registro actualizado exitosamente", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@EditActivity, DetailsActivity::class.java)
                    intent.putExtra("ID", id)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this@EditActivity, "Error al actualizar el registro", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@EditActivity, "Ningún campo puede quedar vacío", Toast.LENGTH_LONG).show()
            }
        }
    }
}