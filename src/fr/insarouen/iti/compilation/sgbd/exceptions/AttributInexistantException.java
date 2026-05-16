package fr.insarouen.iti.compilation.sgbd.exceptions;

public class AttributInexistantException extends AttributException {

    public AttributInexistantException(String message) {
        super(message);
    }

    public AttributInexistantException(String message, Throwable cause) {
        super(message, cause);
    }    
}
