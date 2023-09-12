package com.example.slide04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.AppBarConfiguration
import com.example.slide04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  //  private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val newLayout = layoutInflater.inflate(R.layout.donation_constraint, null)
        setContentView(binding.root)

        var toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        binding.NumberPicker.minValue = 900;
        binding.NumberPicker.maxValue = 1100;
        binding.NumberPicker.value = 999;
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_demo,menu)
        return true
    }
}