package a6.calculator.model;

/**
 * Custom Exception thrown when an overflow occurs in the calculator.
 */
public class OverflowException extends Exception {
    /**
     * Constructs a new custom OverflowException with a default error message.
     */
    public OverflowException() {
        super("Error. An overflow occurred.");
    }
}
