package fr.insarouen.iti.compilation.sgbd.exceptions;

public class TableInexistanteException extends PIAventureException {

    public TableInexistanteException(String message) {
        super(message);
    }

    public TableInexistanteException(String message, Throwable cause) {
        super(message, cause);
    }

}
