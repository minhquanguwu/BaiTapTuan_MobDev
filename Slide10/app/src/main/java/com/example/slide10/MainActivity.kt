package com.example.slide10

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.cursoradapter.widget.SimpleCursorAdapter
import com.example.slide10.MyContentProvider


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        return true
    }

    fun onClickAddDetails(view: View?) {

        // class to add values in the database
        val values = ContentValues()


        val s = findViewById<EditText>(R.id.textName).text
        val stringList = s.split("\n")

        stringList.forEach {
            Log.d("string","$it")
            // fetching text from user
            values.put(MyContentProvider.name, it)

            // inserting into database through content URI
            contentResolver.insert(MyContentProvider.CONTENT_URI, values)

        }


        // displaying a toast message
        Toast.makeText(baseContext, "New Record Inserted", Toast.LENGTH_LONG).show()
    }

    @SuppressLint("Range")
    fun onClickShowDetails(view: View?) {
        // inserting complete table details in this text field
        val resultView = findViewById<View>(R.id.res) as TextView

        // creating a cursor object of the
        // content URI
        val cursor = contentResolver.query(Uri.parse("content://com.example.slide10.provider/users"), null, null, null, null)

        // iteration of the cursor
        // to print whole table
        if (cursor!!.moveToFirst()) {
            val strBuild = StringBuilder()
            while (!cursor.isAfterLast) {
                strBuild.append("""
	
	${cursor.getString(cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}
	""".trimIndent())
                cursor.moveToNext()
            }
            resultView.text = strBuild
        } else {
            resultView.text = "No Records Found"
        }
    }

    fun onClickUpdateDetails(view: View?) {
        // Create a ContentValues object with the updated data
        val updatedValues = ContentValues()
        updatedValues.put(MyContentProvider.name, "Quang")
        val RECORD_ID_TO_UPDATE = "1"

        // Define the Uri for the specific record you want to update
        val recordUri = Uri.parse("content://com.example.slide10.provider/users/$RECORD_ID_TO_UPDATE")

        // Use ContentResolver to update the record
        val rowsAffected = contentResolver.update(
            recordUri, // Uri for the specific record
            updatedValues, // ContentValues with updated data
            null, // Selection (optional)
            null // SelectionArgs (optional)
        )

        if (rowsAffected > 0) {
            // Update was successful
            // You can perform any additional actions here
            Toast.makeText(baseContext, "Record Updated", Toast.LENGTH_LONG).show()
        } else {
            // Update failed
            // Handle the failure or notify the user
            Toast.makeText(baseContext, "Update Failed", Toast.LENGTH_LONG).show()
        }
    }

}
