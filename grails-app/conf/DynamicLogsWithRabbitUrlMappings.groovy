class DynamicLoggingUrlMappings {

    static mappings = { applicationContext ->
        // By Default the controller will be disabled
        if (! applicationContext.grailsApplication.config.dynamiclogging.enable.logLevelController) {
            "/logLevel/$action?"(view: '/error')
        }
    }
}
