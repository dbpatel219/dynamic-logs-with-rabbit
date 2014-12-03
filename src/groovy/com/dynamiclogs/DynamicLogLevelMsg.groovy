package com.dynamiclogs

import grails.converters.JSON

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

    public Map toMap(Map toAdd = null) {
        def root = [appName: appName, logLevel: logLevel, loggerName: loggerName]

        if (toAdd) {
            root.putAll(toAdd)
        }

        return root
    }

    public String toString() {
        return (toMap() as JSON) as String
    }
}
