package a6.calculator;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import a6.calculator.model.StackCalculator;
import a6.calculator.controller.CalculationController;
import a6.calculator.controller.DisplayStates;
import a6.calculator.view.CalculatorView;

/**
 * The MainActivity class that initializes and sets up the calculator interface,
 * handling user interactions through buttons and displays.
 */
public class MainActivity extends AppCompatActivity {

    
    /**
     * Called when the activity is started. Initializes UI components and sets up event listeners.
     *
     * @param savedInstanceState If the activity is being re-initialized after being previously
     *                           shut down, this bundle contains the most recently saved data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.display), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Instantiate Display Texts
        TextView displayView = findViewById(R.id.display);
        TextView stackView = findViewById(R.id.stack);

        CalculatorView cv = new CalculatorView(displayView, stackView);
        StackCalculator sc = new StackCalculator();
        CalculationController cc = new CalculationController(sc, cv, DisplayStates.INPUT);

        // Instantiate Buttons
        Button zeroButton = findViewById(R.id.zero);
        Button oneButton = findViewById(R.id.one);
        Button twoButton = findViewById(R.id.two);
        Button threeButton = findViewById(R.id.three);
        Button fourButton = findViewById(R.id.four);
        Button fiveButton = findViewById(R.id.five);
        Button sixButton = findViewById(R.id.six);
        Button sevenButton = findViewById(R.id.seven);
        Button eightButton = findViewById(R.id.eight);
        Button nineButton = findViewById(R.id.nine);
        Button plusminusButton = findViewById(R.id.plusminus);
        Button clearButton = findViewById(R.id.clear);
        Button enterButton = findViewById(R.id.enter);
        Button addButton = findViewById(R.id.add);
        Button subButton = findViewById(R.id.sub);
        Button mulButton = findViewById(R.id.mul);
        Button divButton = findViewById(R.id.div);

        // Instantiate Button onClickListeners
        zeroButton.setOnClickListener(v -> cc.inputDigit(0));
        oneButton.setOnClickListener(v -> cc.inputDigit(1));
        twoButton.setOnClickListener(v -> cc.inputDigit(2));
        threeButton.setOnClickListener(v -> cc.inputDigit(3));
        fourButton.setOnClickListener(v -> cc.inputDigit(4));
        fiveButton.setOnClickListener(v -> cc.inputDigit(5));
        sixButton.setOnClickListener(v -> cc.inputDigit(6));
        sevenButton.setOnClickListener(v -> cc.inputDigit(7));
        eightButton.setOnClickListener(v -> cc.inputDigit(8));
        nineButton.setOnClickListener(v -> cc.inputDigit(9));

        enterButton.setOnClickListener(v -> cc.inputEnter());
        clearButton.setOnClickListener(v -> cc.inputClear());
        plusminusButton.setOnClickListener(v -> cc.inputPlusMinus());


        addButton.setOnClickListener(v -> cc.performOperation(() -> sc.add()));
        subButton.setOnClickListener(v -> cc.performOperation(() -> sc.subtract()));
        mulButton.setOnClickListener(v -> cc.performOperation(() -> sc.multiply()));
        divButton.setOnClickListener(v -> cc.performOperation(() -> sc.divide()));
    }
}
