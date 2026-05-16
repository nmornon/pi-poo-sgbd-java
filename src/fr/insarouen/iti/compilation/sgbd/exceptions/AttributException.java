package fr.insarouen.iti.compilation.sgbd.exceptions;

public class AttributException extends RuntimeException {

    public AttributException(String message) {
        super(message);
    }

    public AttributException(String message, Throwable cause) {
        super(message, cause);
    }    
}
