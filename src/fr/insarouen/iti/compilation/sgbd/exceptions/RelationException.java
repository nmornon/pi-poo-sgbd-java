package fr.insarouen.iti.compilation.sgbd.exceptions;

/**
 * Classe mère des exceptions sur les relations
 */
public class RelationException extends RuntimeException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public RelationException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public RelationException(String message, Throwable cause) {
        super(message, cause);
    }    
}
