package com.example.slide09

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Shared_Preferences : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editTextName: EditText
    private lateinit var buttonSave: Button
    private lateinit var textViewSavedName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        editTextName = findViewById(R.id.editTextName)
        buttonSave = findViewById(R.id.buttonSave)
        textViewSavedName = findViewById(R.id.textViewSavedName)

        loadSavedName()

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            saveName(name)
            textViewSavedName.text = "Saved Name: $name"
        }
    }

    private fun saveName(name: String) {
        // Get an editor for SharedPreferences
        val editor = sharedPreferences.edit()

        // Store the name with a key "user_name"
        editor.putString("user_name", name)

        // Apply the changes
        editor.apply()
    }

    private fun loadSavedName() {
        val savedName = sharedPreferences.getString("user_name", "")
        textViewSavedName.text = "Saved Name: $savedName"
    }
}