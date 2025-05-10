package a6.calculator.model;

/**
 * Exception thrown when an operation is attempted with insufficient arguments in the stack.
 */
public class NotEnoughArgumentsException extends Exception {

    /**
     * Constructs a new NotEnoughArgumentsException with a custom error message.
     */
    public NotEnoughArgumentsException() {
        super("Error. Not enough arguments.");
    }
}
