package grails.plugin.dynamiclogs

import org.springframework.beans.factory.InitializingBean

class LogLevelService implements InitializingBean {

    static transactional = false

    def grailsApplication

    private String exchange

    def send(DynamicLogLevelMsg message) {
        rabbitSend exchange, "", message.toString()
    }

    void afterPropertiesSet() {
        exchange = grailsApplication.config.dynamiclogging.exchange.name
    }
}
