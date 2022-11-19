package com.musala.drones.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DronesLogger {
    public static final Logger INFO_LOGGER = LogManager.getLogger("infoLogger");
    public static final Logger ERROR_LOGGER = LogManager.getLogger("errorLogger");
    public static final Logger AUDIT_LOGGER = LogManager.getLogger("auditLogger");

    public static void error(String msg) {
        msg = format(msg);
        INFO_LOGGER.error(msg);
        ERROR_LOGGER.error(msg);
    }

    private static String format(String msg) {
        StringBuilder formattedMsg = new StringBuilder();
        StackTraceElement ste = getCallerFromStack();
        formattedMsg.append("[")
                .append(ste.getClassName().substring(ste.getClassName().lastIndexOf(".")))
                .append(".")
                .append(ste.getMethodName())
                .append("]")
                .append(msg);
        return formattedMsg.toString();

    }
    private static StackTraceElement getCallerFromStack(){
        return Thread.currentThread().getStackTrace()[4];
    }

    
}
