package fr.insarouen.iti.compilation.sgbd.exceptions;

public class TableDejaExistanteException extends TableException {

    public TableDejaExistanteException(String message) {
        super(message);
    }

    public TableDejaExistanteException(String message, Throwable cause) {
        super(message, cause);
    }

}
