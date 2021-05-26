package com.multiplatform.sample.shared

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import platform.Foundation.NSLog

class NSLogLogger : Logger() {
    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        NSLog("%s: (%s) %s", severity.name, tag, message)
    }
}