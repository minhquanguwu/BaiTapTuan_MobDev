package com.example.slide06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    private lateinit var contextMenuButton: Button
    private lateinit var popupMenuButton: Button
    private lateinit var buttonAppear: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        // Set the title
        supportActionBar?.title = "Option Menu"

        contextMenuButton = findViewById(R.id.btnContext)
        popupMenuButton = findViewById(R.id.btnPopUp)
        buttonAppear = findViewById<Button>(R.id.button)

        registerForContextMenu(contextMenuButton)

        popupMenuButton.setOnClickListener {
            showPopUpMenu()
        }

    }

    private fun showPopUpMenu() {
        val popupMenu = PopupMenu(this, buttonAppear)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_item1 -> {
                    true
                }
                R.id.menu_item2 -> {
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.it1 -> {
                Toast.makeText(this, "Item 1 is selected", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.it2 -> {
                Toast.makeText(this, "Item 2 is selected", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.it3 -> {
                Toast.makeText(this, "Item 3 is selected", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item1 -> {
                return true
            }
            R.id.menu_item2 -> {
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }


}