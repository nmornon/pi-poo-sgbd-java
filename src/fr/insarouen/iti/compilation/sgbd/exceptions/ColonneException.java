package fr.insarouen.iti.compilation.sgbd.exceptions;

/**
 * Classe mère des exceptions sur les colonnes
 */
public class ColonneException extends SGBDException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public ColonneException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public ColonneException(String message, Throwable cause) {
        super(message, cause);
    }    
}
