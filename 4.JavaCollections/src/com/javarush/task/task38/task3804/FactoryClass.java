package com.javarush.task.task38.task3804;

public class FactoryClass {

    public static Throwable returnException(Enum exception) {

        if (exception == null)
            return new IllegalArgumentException();

        String message = exception.name().charAt(0) + exception.name().substring(1).replace("_", " ").toLowerCase();

        if (exception instanceof ExceptionApplicationMessage) {
            return new Exception(message);
        } else if (exception instanceof ExceptionDBMessage) {
            return new RuntimeException(message);
        } else if (exception instanceof ExceptionUserMessage) {
            return new Error(message);
        }
        return new IllegalArgumentException();
    }
}
