package com.example.slide05

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TabHost
import android.widget.TextView
import android.widget.TimePicker
import java.text.DateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    /**
     * Initialize for DatePickerDialog and TimePickerDialog
     */
    val fmtDateAndTime = DateFormat.getDateTimeInstance()
    val myCalendar = Calendar.getInstance()
    var lblDateAndTime: TextView? = null

    val d = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, monthOfYear)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateLabel()
    }

    val t = TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
        myCalendar.set(Calendar.HOUR, i)
        myCalendar.set(Calendar.MINUTE, i2)
        updateLabel()
    }

    private fun updateLabel() {
        lblDateAndTime?.text = fmtDateAndTime.format(myCalendar.time)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_selector)


        /**
         * Tab Selector
         */
        val tabs = findViewById<TabHost>(R.id.tabhost)
        tabs.setup()

        var spec: TabHost.TabSpec = tabs.newTabSpec("tag1")
        spec.setContent(R.id.tab1)
        spec.setIndicator("1-Clock")
        tabs.addTab(spec)

        spec = tabs.newTabSpec("tag2")
        spec.setContent(R.id.tab2)
        spec.setIndicator("2-Login")
        tabs.addTab(spec)

        tabs.currentTab = 0

        /**
         *  DatePicker and TimePicker
         */
//        lblDateAndTime = findViewById(R.id.tv1)
//
//        val btnDate: Button = findViewById(R.id.button)
//        btnDate.setOnClickListener() {
//            DatePickerDialog(
//                this,
//                d,
//                myCalendar.get(Calendar.YEAR),
//                myCalendar.get(Calendar.MONTH),
//                myCalendar.get(Calendar.DAY_OF_MONTH)
//            ).show()
//        }
//
//        val btnTime: Button = findViewById(R.id.button2)
//        btnTime.setOnClickListener() {
//            TimePickerDialog(
//                this,
//                t,
//                myCalendar.get(Calendar.HOUR),
//                myCalendar.get(Calendar.MINUTE),
//                true
//            ).show()
//        }
    }
}
