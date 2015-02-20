import grails.util.Environment

class DynamicLogsWithRabbitGrailsPlugin {
    def version = "0.5.0"
    def grailsVersion = "2.1 > *"
    def title = "Dynamic Logs With Rabbit Plugin"
    def description = 'Allows you to dynamically change log levels on all instances listening to a particular topic via RabbitMQ Messages.'
    def documentation = "http://grails.org/plugin/dynamic-logs-with-rabbit"
    def license = "APACHE"
    def organization = [ name: "Experience", url: "http://www.expapp.com/" ]
    def developers = [
        [ name: "Dharmesh Patel", email: "dbpatel@expapp.com" ],
        [ name: "David Sawyer", email: "david@expapp.com" ]
    ]
    def issueManagement = [system: "GITHUB", url: "https://github.com/dbpfindexp/dynamic-logs-with-rabbit/issues"]
    def scm = [ url: "https://github.com/dbpfindexp/dynamic-logs-with-rabbit" ]

    def loadAfter = ['services']
    def loadBefore = ['rabbitmq-native']

    def doWithSpring = {
        try {
            log.info("Dynamic Logs With Rabbit - Environment is...${Environment.current.name}")
            application.config.merge(new ConfigSlurper(Environment.current.name).parse(application.classLoader.loadClass('DynamicLogsWithRabbitConfig')))
        } catch (e) {
            log.error('Unable to load DynamicLogsWithRabbitConfig')
        }
    }
}
