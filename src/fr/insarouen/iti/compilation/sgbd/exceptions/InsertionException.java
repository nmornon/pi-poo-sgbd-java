package fr.insarouen.iti.compilation.sgbd.exceptions;

/** Indique une erreur dans l'insertion d'une table */
public class InsertionException extends TableException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public InsertionException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public InsertionException(String message, Throwable cause) {
        super(message, cause);
    }    
}
