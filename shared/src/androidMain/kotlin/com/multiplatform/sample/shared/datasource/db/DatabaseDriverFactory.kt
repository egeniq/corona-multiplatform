package com.multiplatform.sample.shared.datasource.db

import android.content.Context
import com.multiplatform.sample.shared.CoronaDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(CoronaDatabase.Schema, context, "corona.db")
    }
}