package grails.plugin.dynamiclogs

import grails.converters.JSON
import grails.util.Holders
import grails.util.Metadata

import org.apache.log4j.Level
import org.apache.log4j.LogManager

class LogLevelListenerService {
    static rabbitSubscribe = Holders.getConfig().dynamiclogging.exchange.name

    private final String currentAppName = Metadata.current.getApplicationName()

    def pluginManager

    void handleMessage(message) {
        log.info("Received Dynamic Logs change message - ${message}")
        def msg = JSON.parse(message)

        if (msg.appName != currentAppName) {
            return
        }

        def action = msg.msgAction
        switch (action) {
            case 'listPlugins':
                listPlugins(msg)
                break
            case 'changeLogLevel':
                changeLogLevel(msg)
                break
            default :
                log.warn("Dynamic Log Level $action NOT supported.")
        }
    }

    private listPlugins(msg) {
        def plugins = pluginManager.allPlugins
        log.info("Found ${plugins?.size()} plugins")
        log.info(plugins.toString())
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
