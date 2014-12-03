package com.dynamiclogs

import com.dynamiclogs.DynamicLogLevelMsg

class LogLevelController {

    static defaultAction = "index"

    def logLevelService

    def index() {
        [cmd: new LogLevelChangeCommand()]
    }

    def changeLogLevel(LogLevelChangeCommand cmd) {
        if (! cmd.validate()){
            throw new Exception(400, 'Invalid inputs')
        }

        def message = convertCommandToMessage(cmd)
        logLevelService.send(message)

        flash.message = "Log Level Change message sent: ${cmd.toString()}"

        render(view: "index", model: [cmd: cmd])
    }

    private DynamicLogLevelMsg convertCommandToMessage(LogLevelChangeCommand cmd) {
        def message = new DynamicLogLevelMsg(cmd.appName, cmd.level, cmd.loggerName)

        return message
    }
}

class LogLevelChangeCommand {
    String appName
    String loggerName
    String level

    static constraints = {
        appName(nullable: false)
        loggerName(nullable: false)
        level(nullable: false)
    }

    String toString() {
        "$appName - $loggerName - $level"
    }
}
