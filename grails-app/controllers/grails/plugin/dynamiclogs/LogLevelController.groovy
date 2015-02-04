package grails.plugin.dynamiclogs

import grails.util.Metadata

class LogLevelController {

    private static final List<String> LOG_LEVEL_NAMES = ['ALL','DEBUG','ERROR','FATAL','INFO','OFF','TRACE','WARN']
    private static final List<String> LOG_LEVEL_NAMES = ['OFF','FATAL','ERROR','WARN','INFO','DEBUG','TRACE','ALL']
    private static final String APP_NAME = Metadata.current.getApplicationName()

    def logLevelService

    def index() {
        [cmd: new DynamicLogLevelMsg(), applicationName: APP_NAME, logLevelNames: LOG_LEVEL_NAMES]
    }

    def changeLogLevel(DynamicLogLevelMsg cmd) {
        logLevelService.send(cmd)

        flash.message = "Log Level Change message sent: $cmd"

        render(view: "index", model: [cmd: cmd, applicationName: APP_NAME, logLevelNames: LOG_LEVEL_NAMES])
    }
}
