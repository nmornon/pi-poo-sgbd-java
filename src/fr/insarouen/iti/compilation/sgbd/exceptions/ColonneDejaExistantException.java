package fr.insarouen.iti.compilation.sgbd.exceptions;

public class ColonneDejaExistantException extends ColonneException {

    public ColonneDejaExistantException(String message) {
        super(message);
    }

    public ColonneDejaExistantException(String message, Throwable cause) {
        super(message, cause);
    }    
}
