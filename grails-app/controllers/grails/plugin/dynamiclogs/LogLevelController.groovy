package grails.plugin.dynamiclogs

import grails.util.Metadata

class LogLevelController {

    private static final List<String> ACTION_LIST = ['changeLogLevel', 'listPlugins']
    private static final List<String> LOG_LEVEL_NAMES = ['OFF','FATAL','ERROR','WARN','INFO','DEBUG','TRACE','ALL']
    private static final String APP_NAME = Metadata.current.getApplicationName()

    def logLevelService

    def index() {
        [cmd: new DynamicLogLevelMsg(), applicationName: APP_NAME, logLevelNames: LOG_LEVEL_NAMES, actionList: ACTION_LIST]
    }

    def changeLogLevel(DynamicLogLevelMsg cmd) {
        logLevelService.send(cmd)

        flash.message = "Message sent: $cmd"

        render(view: "index", model: [cmd: cmd, applicationName: APP_NAME, logLevelNames: LOG_LEVEL_NAMES, actionList: ACTION_LIST])
    }
}
