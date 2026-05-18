package fr.insarouen.iti.compilation.sgbd.exceptions;

/** Indique qu'une relation ne peut pas être sélectionnée */
public class SelectionException extends RelationException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public SelectionException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public SelectionException(String message, Throwable cause) {
        super(message, cause);
    }    
}
