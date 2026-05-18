package fr.insarouen.iti.compilation.sgbd.exceptions;

/**
 * Classe mère des exceptions du sgbd
 */
public class PIAventureException extends RuntimeException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public PIAventureException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public PIAventureException(String message, Throwable cause) {
        super(message, cause);
    }
}