package com.example.afifa123.sharedpref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        toggle()

        val launch_button : Button = findViewById(R.id.launch_button)
        launch_button.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun spinner(view: View) {}

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
