package com.example.afifa123.sharedpref
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private val SPINNER_KEY = "Spinner"
    private var spinner_button: Int = 0

    private val TOGGLE_KEY = "Toggle"
    private var toggle_button: Boolean = false
    private lateinit var mPreferances: SharedPreferences
    private var shareedPreFile = "com.example.afifa123.sharedpref"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val launch_button: Button = findViewById(R.id.launch_button)
        launch_button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        mPreferances = getSharedPreferences(shareedPreFile, Context.MODE_PRIVATE)
        toggle_button= mPreferances.getBoolean(TOGGLE_KEY,false)
        spinner_button = mPreferances.getInt(SPINNER_KEY,0)
        spinner()
        toggle()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(TOGGLE_KEY, toggle_button)
        outState.putInt(SPINNER_KEY,spinner_button)

    }
    private fun spinner() {
        val names = arrayOf(
            "A", "B","C","D","E","F","G"
            )
        var spinner_button = findViewById(R.id.spinner) as Spinner
        if (spinner_button != null){
            val arrayAdapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,names)
            spinner_button.adapter = arrayAdapter

            mPreferances = getSharedPreferences(shareedPreFile, Context.MODE_PRIVATE)
            spinner_button.setSelection(mPreferances.getInt(SPINNER_KEY,0))

            spinner_button.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    spinner_button.setSelection(position)
                    val selectedItem = parent?.getItemAtPosition(position).toString()
                    if(selectedItem == "A"){

                    }
                    mPreferances.edit()
                        .putInt(SPINNER_KEY,0)
                        .apply()

                    Toast.makeText(this@SettingsActivity, getString(R.string.selected_item)
                            + "" + names[position],Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            }
        }
    }

    private fun toggle() {
        val toggle_button: ToggleButton = findViewById(R.id.toggle_button)
        toggle_button.setOnCheckedChangeListener { buttonView, isChecked ->

            val msg = "Toggle Button is:" + if (isChecked) "ON" else "OFF"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        mPreferances = getSharedPreferences(shareedPreFile, Context.MODE_PRIVATE)
        toggle_button.isChecked = mPreferances.getBoolean(TOGGLE_KEY, false)
        //
        if (toggle_button.isChecked()) {
            val editor = getSharedPreferences(shareedPreFile, Context.MODE_PRIVATE).edit()
            editor.putBoolean(TOGGLE_KEY, true)
            editor.apply()
        } else {
            val editor = getSharedPreferences(shareedPreFile, Context.MODE_PRIVATE).edit()
            editor.putBoolean(TOGGLE_KEY, false)
            editor.apply()
        }

    }

    override fun onPause() {
        super.onPause()
        val preferencesEditor = mPreferances.edit()
        preferencesEditor.putBoolean(TOGGLE_KEY, toggle_button)
        preferencesEditor.putInt(SPINNER_KEY, spinner_button)
        preferencesEditor.apply()
    }

}









