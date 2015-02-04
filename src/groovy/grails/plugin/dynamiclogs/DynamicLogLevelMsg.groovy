package grails.plugin.dynamiclogs

import grails.converters.JSON
import grails.validation.Validateable

@Validateable
class DynamicLogLevelMsg {
    String appName
    String logLevel
    String loggerName
    String msgAction

    static constraints = {
        msgAction blank: false, nullable: false
        appName blank: false, nullable: false
        logLevel blank: true, nullable: true
        loggerName blank: true, nullable: true
    }

    DynamicLogLevelMsg() {}

    DynamicLogLevelMsg(msgAction, appName, logLevel = "", loggerName = "") {
        this.msgAction = msgAction
        this.appName = appName
        this.logLevel = logLevel ?: ""
        this.loggerName = loggerName ?: ""
    }

    Map toMap(Map toAdd = null) {
        loggerName = loggerName ?: ""
        logLevel = logLevel ?: ""
        [msgAction: msgAction, appName: appName, logLevel: logLevel, loggerName: loggerName] + (toAdd ?: [:])
    }

    String toString() {
        toMap() as JSON
    }
}
