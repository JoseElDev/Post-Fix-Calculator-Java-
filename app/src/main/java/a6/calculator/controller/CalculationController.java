package a6.calculator.controller;

import a6.calculator.model.StackCalculator;
import a6.calculator.view.CalculatorView;
import a6.calculator.model.*;

/**
 * The CalculationController class handles user interactions and updates the calculator's display accordingly.
 * It manages input, operations, and state transitions.
 */
public class CalculationController {
    private final StackCalculator calculator;
    private final CalculatorView view;
    private DisplayStates displayState;
    private String input;

    /**
     * Constructs a CalculationController instance with given calculator, view, and initial display state.
     *
     * @param calculator   The stack based calculator model.
     * @param view         The calculator display interface.
     * @param initialState The initial display state.
     */
    public CalculationController(StackCalculator calculator, CalculatorView view, DisplayStates initialState) {
        this.calculator = calculator;
        this.view = view;
        this.displayState = initialState;
        this.input = "0";
    }

    /**
     * Handles digit button presses and updates the user input field accordingly.
     *
     * @param d The digit input by the user.
     */
    public void inputDigit(int d) {
        if (displayState != DisplayStates.INPUT) {
            input = String.valueOf(d);
            displayState = DisplayStates.INPUT;
        } else {
            if (input.equals("0")) {
                input = String.valueOf(d);
            } else {
                input = input + d;
            }
        }
        view.displayInput(input);
        view.displayStack(calculator);
    }

    /**
     * Pushes the currently entered number on to the calculator stack.
     *
     * @throws OverflowException if the value exceeds the allowable range.
     */
    private void inputPush() throws OverflowException {
        try {
            String text = input;
            if (text.endsWith("_")) {
                text = text.substring(0, text.length() - 1);
            }
            int n = Integer.parseInt(text);
            calculator.push(n);
        } catch (NumberFormatException e) {
            throw new OverflowException();
        }
    }

    /**
     * Updates the calculator display with a specified text and state.
     *
     * @param text  The text to display.
     * @param state The new display state.
     */
    private void setDisplay(String text, DisplayStates state) {
        this.displayState = state;
        if (state == DisplayStates.ERROR) {
            view.displayError(text);
        } else {
            view.displayResult(text);
        }
        view.displayStack(calculator);
    }

    /**
     * Performs the specified operation and updates the display accordingly.
     *
     * @param op The operation to execute.
     */
    public void performOperation(Operation op) {
        if (displayState == DisplayStates.INPUT) {
            try {
                inputPush();
            } catch (OverflowException e) {
                setDisplay("Overflow", DisplayStates.ERROR);
                return;
            }
        }
        try {
            op.apply();
            setDisplay(String.valueOf(calculator.peek()), DisplayStates.STACK);
        } catch (NotEnoughArgumentsException e) {
            setDisplay("Not enough args", DisplayStates.ERROR);
        } catch (DivisionByZeroException | OverflowException e) {
            String text;
            if (e instanceof DivisionByZeroException) {
                text = "Division by 0";
            } else {
                text = "Overflow";
            }
            setDisplay(text, DisplayStates.ERROR);
        }
    }

    /**
     * Handles the Enter button press by pushing the current input onto the stack.
     */
    public void inputEnter() {
        if (displayState == DisplayStates.INPUT) {
            try {
                inputPush();
                setDisplay(String.valueOf(calculator.peek()), DisplayStates.STACK);
            } catch (OverflowException e) {
                setDisplay("Overflow", DisplayStates.ERROR);
            }
        } else if (displayState == DisplayStates.STACK) {
            calculator.push(calculator.peek());
            view.displayStack(calculator);
        }
    }

    
    /**
     * Handles the +/- button to toggle the sign of the entered number.
     */
    public void inputPlusMinus() {
        if (displayState == DisplayStates.INPUT) {
            if (input.startsWith("-")) {
                input = input.substring(1);
            } else {
                input = "-" + input;
            }
            setDisplay(input + "_", DisplayStates.INPUT);
        } else {
            input = "-0";
            setDisplay(input + "_", DisplayStates.INPUT);
        }
    }

    /**
     * Clears the calculator stack and resets the display to "0_".
     */
    public void inputClear() {
        calculator.clear();
        input = "0";
        setDisplay("0_", DisplayStates.INPUT);
    }
}
