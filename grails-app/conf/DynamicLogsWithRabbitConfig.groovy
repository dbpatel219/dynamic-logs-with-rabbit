import grails.util.Holders

dynamiclogging.exchange.name = 'logLevelExchange'

rabbitmq {
    queues = {
        exchange name: Holders.getConfig().dynamiclogging.exchange.name, type: fanout, durable: true
    }
}
