package com.dynamiclogs

import grails.converters.JSON
import grails.util.Metadata

import org.apache.log4j.Level
import org.apache.log4j.LogManager
import org.springframework.beans.factory.InitializingBean

class LogLevelListenerService implements InitializingBean {

    static transactional = false

    private String rabbitSubscribe

    private final String currentAppName = Metadata.current.getApplicationName()

    void handleMessage(message) {
        def msg = JSON.parse(message)

        if (msg.appName != currentAppName) {
            return
        }

        def logLevel = Level.toLevel(msg.logLevel)
        def logger = LogManager.getLogger(msg.loggerName)

        if (logger && logLevel) {
            log.info("Changing Log Level $msg.loggerName --> $logLevel")
            logger.level = logLevel
        } else { // Throw Exception or just log it????
            log.error("Either loggerName or logLevel was not a valid input.  $msg")
        }
    }

    void afterPropertiesSet() {
        rabbitSubscribe = grailsApplication.config.dynamiclogging.exchange.name
    }
}
