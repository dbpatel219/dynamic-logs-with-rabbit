import grails.util.Environment

class DynamicLogsWithRabbitGrailsPlugin {
    def version = "0.4.6"
    def grailsVersion = "2.1 > *"
    def title = "Dynamic Logs With Rabbit Plugin"
    def description = 'Allows you to dynamically change log levels on all instances listening to a particular topic via RabbitMQ Messages.'
    def documentation = "http://grails.org/plugin/dynamic-logs-with-rabbit"
    def license = "APACHE"
    def organization = [ name: "Experience", url: "http://www.expapp.com/" ]
    def developers = [[ name: "Dharmesh Patel", email: "dbpatel@expapp.com" ]]
    def issueManagement = [system: "GITHUB", url: "https://github.com/dbpfindexp/dynamic-logs-with-rabbit/issues"]
    def scm = [ url: "https://github.com/dbpfindexp/dynamic-logs-with-rabbit" ]

    def loadAfter = ['services']
    def loadBefore = ['rabbitmq']
    //def loadAfter = ['controllers', 'services', 'domains', 'hibernate', 'spring-security-core']

    def doWithSpring = {
        try {
            log.info("Dynamic Logs With Rabbit - Environment is...${Environment.current.name}")
            application.config.merge(new ConfigSlurper(Environment.current.name).parse(application.classLoader.loadClass('DynamicLogsWithRabbitConfig')))
        } catch (e) {
            log.error('Unable to load DynamicLogsWithRabbitConfig')
        }
    }
}
