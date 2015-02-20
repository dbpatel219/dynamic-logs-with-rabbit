package grails.plugin.dynamiclogs

import com.budjb.rabbitmq.publisher.RabbitMessagePublisher
import org.springframework.beans.factory.InitializingBean

class LogLevelService implements InitializingBean {

    static transactional = false

    def grailsApplication
    RabbitMessagePublisher rabbitMessagePublisher

    private String exchangeStr

    def send(DynamicLogLevelMsg message) {
        rabbitMessagePublisher.send {
            exchange = exchangeStr
            body = message as String
        }
    }

    void afterPropertiesSet() {
        exchangeStr = grailsApplication.config.dynamiclogging.exchange.name
    }
}
