package dynamiclogswithrabbit

import ch.qos.logback.classic.Level
import com.budjb.rabbitmq.consumer.MessageContext
import grails.util.Metadata
import org.apache.log4j.LogManager
import org.slf4j.LoggerFactory

class LogLevelConsumer {
    static rabbitConfig = [exchange: 'logLevelExchange']

    private final String currentAppName = Metadata.current.getApplicationName()

    void handleMessage(Map msg, MessageContext context) {
        log.info("Received Dynamic Logs change message - $msg")

        if (msg.appName != currentAppName) {
            return
        } else {
            changeLogLevel(msg)
        }
    }

    private changeLogLevel(msg) {
        if (msg.logLevel && msg.loggerName) {
            def logLevel = Level.toLevel(msg?.logLevel)
            def logger = LoggerFactory.getILoggerFactory().getLogger(msg?.loggerName)
            if (logger && logLevel) {
                log.info("Changing Log Level $msg.loggerName --> $logLevel")
                logger.level = logLevel
            }
        } else {
            log.error("Either loggerName or logLevel was not a valid input.  $msg")
        }
    }
}
