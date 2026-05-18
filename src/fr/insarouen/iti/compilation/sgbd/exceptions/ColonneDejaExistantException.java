package fr.insarouen.iti.compilation.sgbd.exceptions;

/**
 * Indique qu'une colonne possède déjà l'identifiant spécifié
 */
public class ColonneDejaExistantException extends ColonneException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public ColonneDejaExistantException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public ColonneDejaExistantException(String message, Throwable cause) {
        super(message, cause);
    }    
}
