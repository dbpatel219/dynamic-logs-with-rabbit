package grails.plugin.dynamiclogs

import com.budjb.rabbitmq.publisher.RabbitMessagePublisher
import org.springframework.beans.factory.InitializingBean

class LogLevelService {

    static transactional = false

    RabbitMessagePublisher rabbitMessagePublisher

    def send(DynamicLogLevelMsg message) {
        rabbitMessagePublisher.send {
            exchange = 'logLevelExchange'
            body = message as String
        }
    }
}
