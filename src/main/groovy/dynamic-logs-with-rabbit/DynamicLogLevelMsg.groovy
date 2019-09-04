package dynamic-logs-with-rabbit

import grails.converters.JSON
import grails.validation.Validateable

class DynamicLogLevelMsg implements Validateable {
    String appName
    String logLevel
    String loggerName

    static constraints = {
        appName blank: false, nullable: false
        logLevel blank: true, nullable: true
        loggerName blank: true, nullable: true
    }

    DynamicLogLevelMsg() {}

    DynamicLogLevelMsg(appName, logLevel = "INFO", loggerName = "") {
        this.appName = appName
        this.logLevel = logLevel ?: ""
        this.loggerName = loggerName ?: ""
    }

    Map toMap(Map toAdd = null) {
        loggerName = loggerName ?: ""
        logLevel = logLevel ?: ""
        [appName: appName, logLevel: logLevel, loggerName: loggerName] + (toAdd ?: [:])
    }

    String toString() {
        toMap() as JSON
    }
}
