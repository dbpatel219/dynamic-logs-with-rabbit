package com.dynamiclogs

import grails.util.Holders

class LogLevelService {

    static final String EXCHANGE = Holders.getConfig().dynamiclogging.exchange.name

    def send(DynamicLogLevelMsg message) {
        rabbitSend EXCHANGE, "", message as String
    }
}
