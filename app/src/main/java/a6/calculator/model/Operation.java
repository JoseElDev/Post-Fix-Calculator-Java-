package a6.calculator.model;

/**
 * Represents an arithmetic operation that can be applied to a stack-based calculator.
 */
public interface Operation {

    /**
     * Applies the operation on the calculator stack.
     *
     * @throws NotEnoughArgumentsException If there are insufficient arguments for the operation.
     * @throws OverflowException If the result of the operation causes an overflow.
     * @throws DivisionByZeroException If the operation attempts to divide by zero.
     */
    void apply() throws NotEnoughArgumentsException,
                        OverflowException,
                        DivisionByZeroException;
}
