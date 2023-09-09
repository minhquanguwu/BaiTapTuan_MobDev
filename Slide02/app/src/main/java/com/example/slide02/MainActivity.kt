package com.example.slide02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.NumberPicker
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberPicker: NumberPicker = findViewById(R.id.NumberPicker)
        numberPicker.minValue = 900
        numberPicker.maxValue = 2000
        numberPicker.value = 999
    }


}