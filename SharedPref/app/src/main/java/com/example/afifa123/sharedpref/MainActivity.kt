package com.example.afifa123.sharedpref
import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.afifa123.sharedpref.R.color.*

class MainActivity : AppCompatActivity() {

    private val COUNT_KEY = "Count"
    private val COLOR_KEY = "Color"

    private var mColor: Int = 0
    private var mCount:Int = 0

    private lateinit var  mShowCountTextView:TextView

    private lateinit var mPreferances : SharedPreferences
    private var shareedPreFile = "com.example.afifa123.sharedpref"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize views and colors
        mShowCountTextView = findViewById(R.id.count_textview)
        mColor = ContextCompat.getColor(applicationContext, default_background)

        mPreferances = getSharedPreferences(shareedPreFile, Context.MODE_PRIVATE)

        mCount = mPreferances.getInt(COUNT_KEY,0)
        mShowCountTextView.setText(String.format("%s",mCount))
        mColor = mPreferances.getInt(COLOR_KEY, mColor)
        mShowCountTextView.setBackgroundColor(mColor)

    }

    fun changeBackground(view: View) {
        var color:Int = (view.background as ColorDrawable).color
        mShowCountTextView.setBackgroundColor(color)
        mColor = color

    }
    fun countUp(view: View) {
        mCount++
        mShowCountTextView.setText(String.format("%s",mCount))
    }

    override fun onSaveInstanceState(outState: Bundle) {
       super.onSaveInstanceState(outState)
        outState.putInt(COUNT_KEY, mCount)
        outState.putInt(COLOR_KEY, mColor)
    }

    fun reset(view: View) {
        mCount = 0
        mShowCountTextView.setText(String.format("%s", mCount))
        mColor = ContextCompat.getColor(applicationContext, default_background)
        mShowCountTextView.setBackgroundColor(mColor)
        /*clear preferances*/
        val preferencesEditor = mPreferances.edit()
        preferencesEditor.clear()
        preferencesEditor.apply()
    }

    override fun onPause() {
        super.onPause()
        val preferencesEditor = mPreferances.edit()
        preferencesEditor.putInt(COUNT_KEY, mCount)
        preferencesEditor.putInt(COLOR_KEY, mColor)
        preferencesEditor.apply()
    }
}
