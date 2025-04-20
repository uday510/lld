package com.app.patterns.behavioral.chainofresponsibility;

// It’s a behavioral pattern where a request is passed along a chain of handlers,
// and each handler decides whether to process it or pass it along.

abstract class Logger {
    public static final int INFO = 1;
    public static final int DEBUG = 2;
    public static final int ERROR = 3;

    protected int level;
    protected Logger next;

    public void setNext(Logger nextLogger) {
        this.next = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (next != null) {
            next.logMessage(level, message);
        }
    }

    abstract protected void write(String message);
}

class ConsoleLogger extends Logger {
    public ConsoleLogger(int level) {
        this.level = level;
    }
    protected void write(String message) {
        System.out.println("Console: " + message);
    }
}

class FileLogger extends Logger {
    public FileLogger(int level) {
        this.level = level;
    }
    protected void write(String message) {
        System.out.println("File: " + message);
    }
}

class ErrorLogger extends Logger {
    public ErrorLogger(int level) {
        this.level = level;
    }
    protected void write(String message) {
        System.out.println("Error: " + message);
    }
}

public class LoggerDemo {

    private static Logger getChainOfLoggers() {
        Logger errorLogger = new ErrorLogger(Logger.ERROR);
        Logger consoleLogger = new ConsoleLogger(Logger.DEBUG);
        Logger fileLogger = new FileLogger(Logger.INFO);

        consoleLogger.setNext(fileLogger);
        fileLogger.setNext(errorLogger);

        return consoleLogger;
    }

    public static void main(String[] args) {
        Logger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(Logger.INFO, "This is an information.");
        System.out.println("-------------------------------------------------");
        loggerChain.logMessage(Logger.DEBUG, "This is an information.");
        System.out.println("-------------------------------------------------");
        loggerChain.logMessage(Logger.ERROR, "This is an error message.");

    }

}
