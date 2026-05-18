package fr.insarouen.iti.compilation.sgbd.exceptions;

/** Indique le type n'est pas valide */
public class TypeInvalideException extends SGBDException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public TypeInvalideException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public TypeInvalideException(String message, Throwable cause) {
        super(message, cause);
    }

}
