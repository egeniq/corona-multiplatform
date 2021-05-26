package com.multiplatform.sample.shared.datasource.db

import com.multiplatform.sample.shared.CoronaDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(CoronaDatabase.Schema, "corona.db")
    }
}