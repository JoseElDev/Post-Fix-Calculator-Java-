package a6.calculator.model;

public class OverflowException extends Exception {
    public OverflowException() {
        super("Error. An overflow occurred.");
    }
}
