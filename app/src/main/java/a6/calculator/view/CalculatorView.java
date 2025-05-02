package a6.calculator.view;

import android.widget.TextView;
import java.util.List;

public class CalculatorView {
    private final TextView displayView;
    private final TextView stackView;

    public CalculatorView(TextView displayView, TextView stackView) {
        this.displayView = displayView;
        this.stackView   = stackView;
    }

    // Displays a certain value
    public void displayResult(String value) {
        displayView.setText(value);
    }

    // Displays the result of an operation between two numbers
    public void displayInput(String input) {
        displayView.setText(input + "_");
    }

    // Displays the error message
    public void displayError(String text) {
        displayView.setText(text);
    }

    // Displays the stack above the input
    public void displayStack(List<Integer> elements) {
        StringBuilder sb = new StringBuilder();
        for (Integer v : elements) {
            sb.append(v).append(" ");
        }
        stackView.setText(sb.toString().trim());
    }
}