package fr.insarouen.iti.compilation.sgbd.exceptions;

public class TupleException extends RuntimeException {

    public TupleException(String message) {
        super(message);
    }

    public TupleException(String message, Throwable cause) {
        super(message, cause);
    }    
}
