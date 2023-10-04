package com.example.slide09

import android.provider.BaseColumns

object DatabaseContract {
    // Inner class that defines the table contents
    class UserEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "user"
            const val COLUMN_NAME_NAME = "name"
            const val _ID = BaseColumns._ID
        }
    }
}
