package fr.insarouen.iti.compilation.sgbd.exceptions;

public class ColonneException extends RuntimeException {

    public ColonneException(String message) {
        super(message);
    }

    public ColonneException(String message, Throwable cause) {
        super(message, cause);
    }    
}
