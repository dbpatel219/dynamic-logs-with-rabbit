package dynamic-logs-with-rabbit

import com.budjb.rabbitmq.publisher.RabbitMessagePublisher

class LogLevelService {

    static transactional = false

    RabbitMessagePublisher rabbitMessagePublisher

    def send(DynamicLogLevelMsg message) {
        log.info("Sending a log message $message")
        rabbitMessagePublisher.send {
            exchange = 'logLevelExchange'
            body = message as String
        }
    }
}
