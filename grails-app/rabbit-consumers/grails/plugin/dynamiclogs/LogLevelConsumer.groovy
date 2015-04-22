package grails.plugin.dynamiclogs

import grails.util.Holders
import grails.util.Metadata

import org.apache.log4j.Level
import org.apache.log4j.LogManager

class LogLevelConsumer {
    static rabbitConfig = [exchange: 'logLevelExchange']

    private final String currentAppName = Metadata.current.getApplicationName()

    void handleMessage(Map msg) {
        log.info("Received Dynamic Logs change message - $msg")

        if (msg.appName != currentAppName) {
            return
        }

        def action = msg.msgAction
        switch (action) {
            case 'changeLogLevel':
                changeLogLevel(msg)
                break
            default :
                log.warn("Dynamic Log Level $action NOT supported.")
        }
    }

    private changeLogLevel(msg) {
        if (msg.logLevel && msg.loggerName) {
            def logLevel = Level.toLevel(msg?.logLevel)
            def logger = LogManager.getLogger(msg?.loggerName)
            if (logger && logLevel) {
                log.info("Changing Log Level $msg.loggerName --> $logLevel")
                logger.level = logLevel
            }
        } else {
            log.error("Either loggerName or logLevel was not a valid input.  $msg")
        }
    }
}
