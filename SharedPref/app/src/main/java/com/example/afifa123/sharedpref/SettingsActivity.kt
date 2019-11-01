package com.example.afifa123.sharedpref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        spinner()
        toggle()

        val launch_button : Button = findViewById(R.id.launch_button)
        launch_button.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun spinner() {
        val names = arrayOf(
            "A", "B","C","D","E","F","G"
            )
        val spinner:Spinner = findViewById(R.id.spinner)
        if (spinner != null){
            val arrayAdapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,names)
            spinner.adapter = arrayAdapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(this@SettingsActivity, getString(R.string.selected_item)
                            + "" + names[position],Toast.LENGTH_SHORT).show()
                    text_view.setText(R.string.selected_item)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            }
        }
    }

    private fun toggle(){
        val toggle_button:ToggleButton =  findViewById(R.id.toggle_button)
        toggle_button.setOnCheckedChangeListener{
           buttonView, isChecked ->
            val msg = "Toggle Button is:" + if(isChecked)"ON" else "OFF"
            val text_view : TextView = findViewById(R.id.text_view)
            text_view.setText(msg)
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        }
    }
}
