package fr.insarouen.iti.compilation.sgbd.exceptions;

/**
 * Classe mère des exceptions sur les tables
 */
public class TableException extends SGBDException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public TableException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public TableException(String message, Throwable cause) {
        super(message, cause);
    }
}
