<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="Log Level Change" />
        <title>Change Log4J Log Level</title>
        <style>
            .fieldcontain {
                margin-top: 1em;
            }
        </style>
    </head>
    <body>
        <div id="index-LogLevel" class="content scaffold-create" role="main">
            <h1>Change Log4J Log Level</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${cmd}">
            <ul class="errors" role="alert">
                <g:eachError bean="${cmd}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="changeLogLevel" method="POST">
                <fieldset class="form">
                    <div class="fieldcontain ${hasErrors(bean: cmd, field: 'appName', 'error')} required">
                        <label for="appName">
                            <g:message code="cmd.appName.label" default="App Name" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="appName" required="" value="${cmd?.appName ?: applicationName}"/>
                    </div>
                    <div class="fieldcontain ${hasErrors(bean: cmd, field: 'loggerName', 'error')} required">
                        <label for="loggerName">
                            <g:message code="cmd.loggerName.label" default="Logger Name" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="loggerName" required="" value="${cmd?.loggerName}"/>
                    </div>
                    <div class="fieldcontain ${hasErrors(bean: cmd, field: 'logLevel', 'error')} required">
                        <label for="logLevel">
                            <g:message code="cmd.logLevel.label" default="Log Level" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:select
                            id="logLevel"
                            name='logLevel'
                            value="DEBUG"
                            from="${logLevelNames}"
                            style="width: 10em;">
                        </g:select>
                    </div>
                </fieldset>
                <fieldset class="buttons" style="text-align: center;">
                    <g:submitButton name="change" style="height: 4em; margin-top: 1em;" class="save" value="Change Log Level" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
