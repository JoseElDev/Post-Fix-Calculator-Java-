package a6.calculator.model;

public class NotEnoughArgumentsException extends Exception {
    public NotEnoughArgumentsException() {
        super("Error. Not enough arguments.");
    }
}
