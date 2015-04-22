import grails.util.Holders

rabbitmq {
    queues = {
        exchange name: 'logLevelExchange', type: 'fanout', durable: true
    }
}
