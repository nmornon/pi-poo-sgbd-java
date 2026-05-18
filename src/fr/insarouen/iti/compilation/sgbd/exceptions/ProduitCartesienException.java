package fr.insarouen.iti.compilation.sgbd.exceptions;

/** Indique que le produit cartésien est impossible entre deux relations */
public class ProduitCartesienException extends RelationException {

    /**
     * Crée une exception avec un message
     *
     * @param message message décrivant l'erreur
     */
    public ProduitCartesienException(String message) {
        super(message);
    }

    /**
     * Crée une exception avec un message et une cause
     *
     * @param message message décrivant l'erreur
     * @param cause cause de l'exception
     */
    public ProduitCartesienException(String message, Throwable cause) {
        super(message, cause);
    }    
}
