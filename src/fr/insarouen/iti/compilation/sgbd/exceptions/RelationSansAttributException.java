package fr.insarouen.iti.compilation.sgbd.exceptions;

public class RelationSansAttributException extends RelationException {

    public RelationSansAttributException(String message) {
        super(message);
    }

    public RelationSansAttributException(String message, Throwable cause) {
        super(message, cause);
    }    
}
