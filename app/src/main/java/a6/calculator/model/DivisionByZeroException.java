package a6.calculator.model;

/**
 * Custom exception thrown when a division by zero occurs in the calculator.
 */
public class DivisionByZeroException extends Exception {
    /**
     * Constructs a new custom DivisionByZeroException with a default error message.
     */
    public DivisionByZeroException() {
        super("Error. Division by zero is not allowed.");
    }
}
