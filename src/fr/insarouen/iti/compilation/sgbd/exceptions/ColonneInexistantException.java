package fr.insarouen.iti.compilation.sgbd.exceptions;

public class ColonneInexistantException extends ColonneException {

    public ColonneInexistantException(String message) {
        super(message);
    }

    public ColonneInexistantException(String message, Throwable cause) {
        super(message, cause);
    }    
}
