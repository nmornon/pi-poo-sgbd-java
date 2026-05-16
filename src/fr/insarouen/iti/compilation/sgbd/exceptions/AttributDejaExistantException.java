package fr.insarouen.iti.compilation.sgbd.exceptions;

public class AttributDejaExistantException extends AttributException {

    public AttributDejaExistantException(String message) {
        super(message);
    }

    public AttributDejaExistantException(String message, Throwable cause) {
        super(message, cause);
    }    
}
