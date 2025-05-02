package a6.calculator.controller;

import a6.calculator.model.StackCalculator;
import a6.calculator.view.CalculatorView;
import a6.calculator.model.*;

public class CalculationController {
    private final StackCalculator calculator;
    private final CalculatorView view;
    private DisplayStates displayState;
    private String input;


    public CalculationController(StackCalculator calculator, CalculatorView view, DisplayStates initialState) {
        this.calculator = calculator;
        this.view = view;
        this.displayState = initialState;
        this.input = "0";
    }

    // Handles the digit buttons on the calculator.
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

    // Pushes digits to the stack.
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

    // Updates both of the displays.
    private void setDisplay(String text, DisplayStates state) {
        this.displayState = state;
        if (state == DisplayStates.ERROR) {
            view.displayError(text);
        } else {
            view.displayResult(text);
        }
        view.displayStack(calculator);
    }

    // Performs a given operation.
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

    // Handles the Enter button. It tries to push the number and checks for overflow.
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

    // Handles the +/- button.
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

    // Handles the Clear button. It clears the stack and sets the display to "0_".
    public void inputClear() {
        calculator.clear();
        input = "0";
        setDisplay("0_", DisplayStates.INPUT);
    }
}
