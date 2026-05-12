package fr.insarouen.iti.compilation.sgbd.exceptions;

public class PIAventureException extends RuntimeException {

    public PIAventureException(String message) {
        super(message);
    }

    public PIAventureException(String message, Throwable cause) {
        super(message, cause);
    }
}