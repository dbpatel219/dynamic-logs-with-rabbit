class DynamicLogsGrailsPlugin {
    def version = "0.2"
    def grailsVersion = "2.1 > *"
    def title = "Dynamic Logs Plugin"
    def description = 'Allows you to dynamically change log levels on all instances listening to a particular topic via RabbitMQ Messages.'
    def documentation = "http://grails.org/plugin/dynamic-logs"
    def license = "APACHE"
    def organization = [ name: "Experience", url: "http://www.expapp.com/" ]
    def developers = [[ name: "Dharmesh Patel", email: "dbpatel@expapp.com" ]]
    def issueManagement = [system: "GITHUB", url: "https://github.com/dbpfindexp/dynamic-logs/issues"]
    def scm = [ url: "https://github.com/dbpfindexp/dynamic-logs" ]

    def doWithSpring = {
        try {
            application.config.merge(new ConfigSlurper().parse(application.classLoader.loadClass('DynamicLoggingConfig')))
        } catch (e) {
            log.error('Unable to load DynamicLoggingConfig')
        }
    }
}
