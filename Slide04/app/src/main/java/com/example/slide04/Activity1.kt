package com.example.slide04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker

class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        var numberPicker = findViewById<NumberPicker>(R.id.NumberPicker)

        numberPicker.minValue = 900;
        numberPicker.maxValue = 1100;
        numberPicker.value = 999;
    }
}