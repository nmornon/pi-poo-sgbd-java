package fr.insarouen.iti.compilation.sgbd.exceptions;

public class TableException extends PIAventureException {

    public TableException(String message) {
        super(message);
    }

    public TableException(String message, Throwable cause) {
        super(message, cause);
    }

}
