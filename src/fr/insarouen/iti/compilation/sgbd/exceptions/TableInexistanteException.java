package fr.insarouen.iti.compilation.sgbd.exceptions;

public class TableInexistanteException extends ITIAventureException {

    public TableInexistanteException(String message) {
        super(message);
    }

    public TableInexistanteException(String message, Throwable cause) {
        super(message, cause);
    }

}
