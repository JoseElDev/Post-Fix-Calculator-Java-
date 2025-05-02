package a6.calculator.model;

import java.util.LinkedList;

public class StackCalculator extends LinkedList<Integer> {
    public void add() throws NotEnoughArgumentsException, OverflowException {
        // Check for NotEnoughArgumentsException
        if (this.size() < 2) {
            throw new NotEnoughArgumentsException();
        }

        // Popping two numbers to add from the stack
        int sum;
        int a = this.pop();
        int b = this.pop();

        // Check for OverflowException after handling addition
        try {
            sum = Math.addExact(a, b);
        } catch (ArithmeticException ae) {
            throw new OverflowException();
        }

        this.push(sum);
    }

    public void subtract() throws NotEnoughArgumentsException, OverflowException  {
        // Check for NotEnoughArgumentsException
        if (this.size() < 2) {
            throw new NotEnoughArgumentsException();
        }

        int difference;
        int b = this.pop();
        int a = this.pop();

        // Check for OverflowException after handling subtraction
        try {
            difference = Math.subtractExact(a, b);
        } catch (ArithmeticException ae) {
            throw new OverflowException();
        }

        this.push(difference);
    }

    public void multiply() throws NotEnoughArgumentsException, OverflowException {
        // Check for NotEnoughArgumentsException
        if (this.size() < 2) {
            throw new NotEnoughArgumentsException();
        }

        int product;
        int a = this.pop();
        int b = this.pop();

        // Check for OverflowException after handling multiplication
        try {
            product = Math.multiplyExact(a, b);
        } catch (ArithmeticException ae) {
            throw new OverflowException();
        }

        this.push(product);
    }

    public void divide() throws NotEnoughArgumentsException, DivisionByZeroException, OverflowException {
        // Check for NotEnoughArgumentsException
        if (this.size() < 2) {
            throw new NotEnoughArgumentsException();
        }

        // Check for DivisionByZeroException
        int b = this.pop();
        if (b == 0) {
            throw new DivisionByZeroException();
        }

        int quotient;
        int a = this.pop();

        // Check for OverflowException after handling division
        try {
            quotient = Math.divideExact(a, b);
        } catch (ArithmeticException ae) {
            throw new OverflowException();
        }

        this.push(quotient);
    }
}
