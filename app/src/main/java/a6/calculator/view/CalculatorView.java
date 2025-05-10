package a6.calculator.view;

import android.widget.TextView;
import java.util.List;

/**
 * Represents the user interface for the calculator that handles display updates.
 */
public class CalculatorView {
    private final TextView displayView;
    private final TextView stackView;

    /**
     * Constructs a CalculatorView instance.
     *
     * @param displayView The TextView to display calculator results.
     * @param stackView   The TextView to display the stack contents.
     */
    public CalculatorView(TextView displayView, TextView stackView) {
        this.displayView = displayView;
        this.stackView   = stackView;
    }

    /**
     * Displays a result value on the calculator screen.
     *
     * @param value The value to display.
     */
    public void displayResult(String value) {
        displayView.setText(value);
    }

    /**
     * Displays the user's input with an "_" suffix.
     *
     * @param input The input value to display.
     */
    public void displayInput(String input) {
        displayView.setText(input + "_");
    }

    /**
     * Displays an error message on the calculator screen.
     *
     * @param text The error message to display.
     */
    public void displayError(String text) {
        displayView.setText(text);
    }

    /**
     * Displays the stack contents above the input field.
     *
     * @param elements The list of integers in the calculator stack.
     */
    public void displayStack(List<Integer> elements) {
        StringBuilder sb = new StringBuilder();
        for (Integer v : elements) {
            sb.append(v).append(" ");
        }
        stackView.setText(sb.toString().trim());
    }
}
