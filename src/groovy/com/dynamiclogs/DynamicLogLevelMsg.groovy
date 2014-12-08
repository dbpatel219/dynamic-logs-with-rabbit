package com.dynamiclogs

import grails.converters.JSON
import grails.validation.Validateable

@Validateable
class DynamicLogLevelMsg {
    String appName
    String logLevel
    String loggerName

    DynamicLogLevelMsg() {}

    DynamicLogLevelMsg(appName, logLevel, loggerName) {
        this.appName = appName
        this.logLevel = logLevel
        this.loggerName = loggerName
    }

    Map toMap(Map toAdd = null) {
        [appName: appName, logLevel: logLevel, loggerName: loggerName] + (toAdd ?: [:])
    }

    String toString() {
        toMap() as JSON
    }
}
