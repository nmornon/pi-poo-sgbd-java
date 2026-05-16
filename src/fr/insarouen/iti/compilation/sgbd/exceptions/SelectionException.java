package fr.insarouen.iti.compilation.sgbd.exceptions;

public class SelectionException extends RelationException {

    public SelectionException(String message) {
        super(message);
    }

    public SelectionException(String message, Throwable cause) {
        super(message, cause);
    }    
}
