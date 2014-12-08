dynamic-logs-with-rabbit
============

Change Application Log Levels via Rabbit MQ messages at Runtime.

- Great for large scale systems where you'd like to dynamically change your log levels on multiple application instances without requiring code changes.


Installation
-------

Upon install, this will create a new Rabbit Exchange for you called `logLevelExchange` of type fanout


Usage
-------

**Via Controller**

1. Enable the controller from your application's **Config.groovy**

    dynamiclogging.enable.logLevelController = true

2. A controller `LogLevelController` and view are provided to change the log levels from.  Simply go to _http[s]://yourapp.com/logLevel_

3. Simply fill in the form and submit.  App Name is the application you would like to change the log level for. `LogLevelListenerService` matches Grails **application.properties "app.name"** against the one that is passed in from the form.


**NOTE:** Highly recommend you lock down `logLevel` endpoint via Spring Security.

**Via Service**

1. Inject logLevelService into your services or controllers :
2. Create a `DynamicLogLevelMsg`

- `appName` is the Application name you want to target.  Uses Grails **application.properties "app.name"**
- `loggerName` is the package or class you would like to change the log level for
- `logLevel` is log level as a String you would like to change to: *['ALL','DEBUG','ERROR','FATAL','INFO','OFF','TRACE','WARN']*

**Example**

    class Foo {
        def logLevelService

        def changeAllServiceLogLevelsToInfo() {
            logLevelService.send new DynamicLogLevelMsg("myApp", "grails.app.services", "INFO")
        }
    }

### Contributors

Thanks to [Burt Beckwith](https://github.com/burtbeckwith) for their contributions
