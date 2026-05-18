package fr.insarouen.iti.compilation.sgbd.exceptions;

/** Indique une exception sur le tuple */
public class TupleException extends RuntimeException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public TupleException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public TupleException(String message, Throwable cause) {
        super(message, cause);
    }    
}
