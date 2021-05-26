package com.multiplatform.sample.shared

import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger

/**
 * Created by Dima Kovalenko on 3/18/21.
 */

actual fun getKermit(): Kermit {
    return Kermit(CommonLogger(), LogcatLogger())
}
