package fr.insarouen.iti.compilation.sgbd.exceptions;

public class RelationException extends RuntimeException {

    public RelationException(String message) {
        super(message);
    }

    public RelationException(String message, Throwable cause) {
        super(message, cause);
    }    
}
