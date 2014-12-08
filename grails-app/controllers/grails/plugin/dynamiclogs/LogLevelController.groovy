package com.dynamiclogs

import grails.util.Metadata

class LogLevelController {

    private static final List<String> LOG_LEVEL_NAMES = ['ALL','DEBUG','ERROR','FATAL','INFO','OFF','TRACE','WARN']
    private static final String APP_NAME = Metadata.current.getApplicationName()

    def logLevelService

    def index() {
        [cmd: new DynamicLogLevelMsg(), applicationName: APP_NAME, logLevelNames: LOG_LEVEL_NAMES]
    }

    def changeLogLevel(DynamicLogLevelMsg cmd) {
        if (cmd.hasErrors()) {
            throw new Exception(400, 'Invalid inputs')
        }

        logLevelService.send(cmd)

        flash.message = "Log Level Change message sent: $cmd"

        render(view: "index", model: [cmd: cmd, applicationName: APP_NAME, logLevelNames: LOG_LEVEL_NAMES])
    }
}
