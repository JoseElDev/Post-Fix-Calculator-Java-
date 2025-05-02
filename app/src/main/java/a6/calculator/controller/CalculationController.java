package a6.calculator.controller;

import android.widget.TextView;
import a6.calculator.model.StackCalculator;
import a6.calculator.model.NotEnoughArgumentsException;
import a6.calculator.model.OverflowException;
import a6.calculator.model.DivisionByZeroException;
import a6.calculator.model.Operation;


public class CalculationController {
    private final StackCalculator calculator;
    private DisplayStates displayState;
    private String input;
    private final TextView displayView;
    private final TextView stackView;

    public CalculationController(StackCalculator calculator, TextView displayView, TextView stackView, DisplayStates initialState) {
        this.calculator = calculator;
        this.displayView = displayView;
        this.stackView = stackView;
        this.displayState = initialState;
        this.input = "0";
    }

    public void inputDigit(int d) {
        if (displayState != DisplayStates.INPUT) {
            input = String.valueOf(d);
            displayState  = DisplayStates.INPUT;
        } else {
            if (input.equals("0")) {
                input = String.valueOf(d);
            } else {
                input = input + d;
            }
        }
        displayView.setText(input + "_");
        updateStackView();
    }

    private void inputPush() throws OverflowException {
        try {
            String text = displayView.getText().toString();
            if (text.endsWith("_")) {
                text = text.substring(0, text.length() - 1);
            }
            int n = Integer.parseInt(text);
            calculator.push(n);
        } catch (NumberFormatException e) {
            throw new OverflowException();
        }
    }

    private void updateStackView() {
        StringBuilder sb = new StringBuilder();
        for (Integer val : calculator) {
            sb.append(val).append(" ");
        }
        stackView.setText(sb.toString().trim());
    }

    private void setDisplay(String text, DisplayStates state) {
        this.displayState = state;
        displayView.setText(text);
        updateStackView();
    }

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
            updateStackView();
        }
    }

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

    public void inputClear() {
        calculator.clear();
        input = "0";
        setDisplay("0_", DisplayStates.INPUT);
    }
}
