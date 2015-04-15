class DynamicLogsWithRabbitBootStrap {
    def init = { servletContext ->
        log.info("Dynamic Logs With Rabbit Plugin Bootstrap...")
    }

    def destroy = {

    }
}
