package fr.insarouen.iti.compilation.sgbd.exceptions;

public class RelationSansColonneException extends RelationException {

    public RelationSansColonneException(String message) {
        super(message);
    }

    public RelationSansColonneException(String message, Throwable cause) {
        super(message, cause);
    }    
}
