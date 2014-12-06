package com.dynamiclogs

import grails.util.Metadata

class LogLevelController {

    private static final List<String> LOG_LEVEL_NAMES = ['ALL','DEBUG','ERROR','FATAL','INFO','OFF','TRACE','WARN']
    private static final String APP_NAME = Metadata.current.getApplicationName()

    def logLevelService

    def index() {
        [cmd: new LogLevelChangeCommand(), applicationName: APP_NAME, logLevelNames: LOG_LEVEL_NAMES]
    }

    def changeLogLevel(LogLevelChangeCommand cmd) {
        if (cmd.hasErrors()) {
            throw new Exception(400, 'Invalid inputs')
        }

        def message = convertCommandToMessage(cmd)
        logLevelService.send(message)

        flash.message = "Log Level Change message sent: $cmd"

        render(view: "index", model: [cmd: cmd, applicationName: APP_NAME, logLevelNames: LOG_LEVEL_NAMES])
    }

    private DynamicLogLevelMsg convertCommandToMessage(LogLevelChangeCommand cmd) {
        new DynamicLogLevelMsg(cmd.appName, cmd.level, cmd.loggerName)
    }
}

class LogLevelChangeCommand {
    String appName
    String loggerName
    String level

    String toString() {
        "$appName - $loggerName - $level"
    }
}
