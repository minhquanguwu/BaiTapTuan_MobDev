package com.example.slide09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader

class Internal_Storage : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var buttonSave: Button
    private lateinit var textViewSavedName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

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
        try {
            // Open a private file for writing
            val fileOutputStream = openFileOutput("user_name.txt", MODE_PRIVATE)

            // Write the name to the file
            fileOutputStream.write(name.toByteArray())

            // Close the file
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadSavedName() {
        try {
            // Open the private file for reading
            val fileInputStream = openFileInput("user_name.txt")

            // Read the contents of the file
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val savedName = bufferedReader.readLine()

            // Close the file
            fileInputStream.close()

            // Update the TextView to display the saved name
            textViewSavedName.text = "Saved Name: $savedName"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}