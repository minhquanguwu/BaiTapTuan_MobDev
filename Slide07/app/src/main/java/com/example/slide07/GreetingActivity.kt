package com.example.slide07

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GreetingActivity : AppCompatActivity() {
    private lateinit var btnBack : Button
    private lateinit var tvMessage : TextView
    var fullName : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)

        btnBack = findViewById<Button>(R.id.button2)
        tvMessage = findViewById<TextView>(R.id.tvMessage)

        var intent : Intent = this.intent
        fullName = intent.getStringExtra("fullName").toString()
        tvMessage.setText(intent.getStringExtra("message"))

        btnBack.setOnClickListener {
            this.onBackPressed()
        }
    }

    override fun finish() {
        //Prepare date
        val intent : Intent = Intent(this, MainActivity::class.java)
        var feedback = "OK, Hello " + this.fullName + ". How are you?"
        intent.putExtra("feedback",feedback)

        //Return data
        setResult(RESULT_OK, intent)
        super.finish()
    }
}