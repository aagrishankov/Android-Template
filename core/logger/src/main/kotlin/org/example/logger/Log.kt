package org.example.logger

import co.touchlab.kermit.BaseLogger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.loggerConfigInit
import co.touchlab.kermit.platformLogWriter
import org.example.env.Environment

object Log : BaseLogger(config = loggerConfigInit(platformLogWriter())) {

    private const val NAME = "ExampleLogger"

    fun debug(msg: () -> String) {
        if (Environment.IS_DEV_STAGE)
            log(Severity.Debug, NAME, null, msg())
    }

    fun error(e: Throwable? = null, text: () -> String) {
        if (Environment.IS_DEV_STAGE)
            log(Severity.Error, NAME, e, text())
    }

    fun info(msg: () -> String) {
        if (Environment.IS_DEV_STAGE)
            log(Severity.Info, NAME, null, msg())
    }

    fun warn(msg: () -> String) {
        if (Environment.IS_DEV_STAGE)
            log(Severity.Warn, NAME, null, msg())
    }
}
