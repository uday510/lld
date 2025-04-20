package com.app.patterns.structural.adapter;

interface AppLogger {
    void logInfo(String message);
    void logError(String message);
}

class LegacyLogger {
    public void writeToLog(String message, String severity) {
        System.out.println("[ " + severity + " ]: " + message);
    }
}

class LegacyLoggerAdapter implements AppLogger {
    private final LegacyLogger legacyLogger;

    public LegacyLoggerAdapter(LegacyLogger legacyLogger) {
        this.legacyLogger = legacyLogger;
    }

    @Override
    public void logInfo(String message) {
        legacyLogger.writeToLog(message, "INFO");
    }

    @Override
    public void logError(String message) {
        legacyLogger.writeToLog(message, "ERROR");
    }
}

public class LoggerClient {

    public static void main(String[] args) {
        LegacyLogger legacyLogger = new LegacyLogger();
        AppLogger logger = new LegacyLoggerAdapter(legacyLogger);

        logger.logInfo("This is an info message.");
        logger.logError("This is an error message.");
    }
}
