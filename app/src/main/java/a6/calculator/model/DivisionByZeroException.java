package a6.calculator.model;

public class DivisionByZeroException extends Exception {
    public DivisionByZeroException() {
        super("Error. Division by zero is not allowed.");
    }
}
