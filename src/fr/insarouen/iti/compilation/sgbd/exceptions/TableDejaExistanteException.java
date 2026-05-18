package fr.insarouen.iti.compilation.sgbd.exceptions;

/** Indique qu'une table existe déjà */
public class TableDejaExistanteException extends TableException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public TableDejaExistanteException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public TableDejaExistanteException(String message, Throwable cause) {
        super(message, cause);
    }

}
