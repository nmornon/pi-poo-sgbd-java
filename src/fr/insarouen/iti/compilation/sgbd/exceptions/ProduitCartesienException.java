package fr.insarouen.iti.compilation.sgbd.exceptions;

public class ProduitCartesienException extends RelationException {

    public ProduitCartesienException(String message) {
        super(message);
    }

    public ProduitCartesienException(String message, Throwable cause) {
        super(message, cause);
    }    
}
