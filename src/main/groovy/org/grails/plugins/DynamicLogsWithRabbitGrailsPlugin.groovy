package org.grails.plugins

import grails.plugins.*
import grails.util.Environment

class DynamicLogsWithRabbitGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.2.9 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def title = "Dynamic Logs With Rabbit" // Headline display name of the plugin
    def author = "Dharmesh Patel"
    def authorEmail = "dbpatel219@gmail.com"
    def description = '''\
Allows you to dynamically change log levels on all instances listening to a particular topic via RabbitMQ Messages.
'''
    def profiles = ['web']

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/dynamic-logs-with-rabbit"

    def license = "APACHE"

    // Any additional developers beyond the author specified above.
    def developers = [
            [ name: "Dharmesh Patel", email: "dbpatel219@gmail.com" ],
            [ name: "David Sawyer", email: "david@expapp.com" ]
    ]

    def issueManagement = [system: "GITHUB", url: "https://github.com/dbpatel219/dynamic-logs-with-rabbit/issues"]
    def scm = [ url: "https://github.com/dbpatel219/dynamic-logs-with-rabbit" ]

    List loadAfter = ['controllers', 'services', 'domains', 'hibernate', 'spring-security-core']
    List loadBefore = ['rabbitmq-native']
    Closure doWithSpring() { {->
            try {
                log.info("Dynamic Logs With Rabbit - Environment is...${Environment.current.name}")
            } catch (e) {
                log.error('Unable to load DynamicLogsWithRabbitConfig')
            }
        }
    }
}
