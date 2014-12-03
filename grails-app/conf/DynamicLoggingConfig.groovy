
dynamiclogging.exchange.name = 'logLevelExchange'

rabbitmq {
    queues = {
        exchange name: "${dynamiclogging.exchange.name}", type: fanout, durable: true, {}
    }
}
