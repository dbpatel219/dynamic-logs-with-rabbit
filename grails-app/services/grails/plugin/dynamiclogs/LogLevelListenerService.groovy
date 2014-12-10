package grails.plugin.dynamiclogs

import grails.converters.JSON
import grails.util.Holders
import grails.util.Metadata

import org.apache.log4j.Level
import org.apache.log4j.LogManager

class LogLevelListenerService {
    static rabbitSubscribe = Holders.getConfig().dynamiclogging.exchange.name

    private final String currentAppName = Metadata.current.getApplicationName()

    void handleMessage(message) {
        log.info("Recieved Log Level change message - ${message}")
        def msg = JSON.parse(message)

        if (msg.appName != currentAppName) {
            return
        }

        def logLevel = Level.toLevel(msg.logLevel)
        def logger = LogManager.getLogger(msg.loggerName)

        if (logger && logLevel) {
            log.info("Changing Log Level $msg.loggerName --> $logLevel")
            logger.level = logLevel
        } else {
            log.error("Either loggerName or logLevel was not a valid input.  $msg")
        }
    }
}
