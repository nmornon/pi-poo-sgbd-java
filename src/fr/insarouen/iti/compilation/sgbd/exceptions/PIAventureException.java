package fr.insarouen.iti.prog.aventure;

public class PIAventureException extends Exception{

    public PIAventureException(String message) {
        super(message);
    }

    public PIAventureException(String message, Throwable cause) {
        super(message, cause);
    }
}