package fr.insarouen.iti.compilation.sgbd.exceptions;

/**
 * Indique qu'une table spécifiée est inexistante
 */
public class TableInexistanteException extends TableException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public TableInexistanteException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public TableInexistanteException(String message, Throwable cause) {
        super(message, cause);
    }
}
