package com.dynamiclogs

import grails.converters.JSON
import grails.util.Holders

import org.apache.log4j.Level
import org.apache.log4j.LogManager

class LogLevelListenerService {
    static rabbitSubscribe = Holders.getConfig().dynamiclogging.exchange.name

    static final String CURRENT_APP_NAME = grails.util.Metadata.current.getApplicationName()

    void handleMessage(message) {
        def msg = JSON.parse(message)
        def logLevel = Level.toLevel(msg.logLevel)
        boolean shouldChange = msg.appName == CURRENT_APP_NAME
        def logger = LogManager.getLogger(msg.loggerName)

        if (logger && logLevel && shouldChange) {
            log.info("Changing Log Level ${msg.loggerName} --> $logLevel")
            logger.level = logLevel
        } else { // Throw Exception or just log it????
            log.error("Either loggerName or logLevel was not a valid input.  $msg")
        }
    }
}
