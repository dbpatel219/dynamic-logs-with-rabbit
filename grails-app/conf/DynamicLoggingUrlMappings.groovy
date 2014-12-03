import grails.util.Holders

class DynamicLoggingUrlMappings {

    static mappings = {
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }

        // By Default the controller will be disabled
        if (! (Holders.getConfig().dynamiclogging.enable.logLevelController == true)) {
            "/logLevel"(view: '/error')
            "/logLevel/changeLogLevel"(view: '/error')
        }

        "/"(view:"/index")
        "500"(view:'/error')
    }
}
