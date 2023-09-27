package com.example.slide09

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SQLite : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var buttonAdd: Button
    private lateinit var textViewSavedName: TextView

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        editTextName = findViewById(R.id.editTextName)
        buttonAdd = findViewById(R.id.buttonAdd) // Updated button reference
        textViewSavedName = findViewById(R.id.textViewSavedName)

        // Initialize the SQLite database helper
        dbHelper = DatabaseHelper(this)

        // Load and display all saved names
        displayAllNames()

        buttonAdd.setOnClickListener {
            // Get the name from the EditText
            val name = editTextName.text.toString()

            // Save the name to the database
            saveName(name)

            // Reload and display all saved names
            displayAllNames()
        }
    }

    private fun saveName(name: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(DatabaseContract.UserEntry.COLUMN_NAME_NAME, name)

        // Insert the name into the database
        db.insert(DatabaseContract.UserEntry.TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    private fun displayAllNames() {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(DatabaseContract.UserEntry.COLUMN_NAME_NAME)
        val cursor: Cursor = db.query(
            DatabaseContract.UserEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val stringBuilder = StringBuilder()
        while (cursor.moveToNext()) {
            val savedName = cursor.getString(cursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_NAME_NAME))
            stringBuilder.append(savedName).append("\n")
        }

        // Update the TextView to display all saved names
        textViewSavedName.text = if (stringBuilder.isNotEmpty()) stringBuilder.toString() else "No names saved."

        cursor.close()
        db.close()
    }
}

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${DatabaseContract.UserEntry.TABLE_NAME} (" +
                    "${DatabaseContract.UserEntry._ID} INTEGER PRIMARY KEY," +
                    "${DatabaseContract.UserEntry.COLUMN_NAME_NAME} TEXT)"

        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database schema upgrades here if needed
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MyAppDatabase.db"
    }
}

