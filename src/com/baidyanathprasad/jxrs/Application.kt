package com.baidyanathprasad.jxrs

import org.glassfish.jersey.logging.LoggingFeature
import org.glassfish.jersey.server.ResourceConfig
import java.util.logging.Level
import java.util.logging.Logger

class Application : ResourceConfig() {
    init {
        packages(this.javaClass.`package`?.name + ".resources")
//        register(LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
//                Level.INFO,
//                LoggingFeature.Verbosity.PAYLOAD_ANY,
//                Integer.MAX_VALUE)
//        )
    }
}
