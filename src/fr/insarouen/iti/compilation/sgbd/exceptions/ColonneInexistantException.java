package fr.insarouen.iti.compilation.sgbd.exceptions;

/**
 * Indique qu'une colonne spécifiée est inexistante
 */
public class ColonneInexistantException extends ColonneException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public ColonneInexistantException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public ColonneInexistantException(String message, Throwable cause) {
        super(message, cause);
    }    
}
