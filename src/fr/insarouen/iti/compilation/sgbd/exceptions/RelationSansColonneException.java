package fr.insarouen.iti.compilation.sgbd.exceptions;

/** Indique que la relation doit posséder au moins une colonne */
public class RelationSansColonneException extends RelationException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public RelationSansColonneException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public RelationSansColonneException(String message, Throwable cause) {
        super(message, cause);
    }    
}
