package android.math.uni.lodz.pl.calculator.activities;

import android.app.Activity;
import android.content.Intent;
import android.math.uni.lodz.pl.calculator.R;
import android.math.uni.lodz.pl.calculator.services.CalculatorService;
import android.math.uni.lodz.pl.calculator.services.impl.DefaultCalculatorService;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main activity. Calculates two numbers and send result to other activity.
 *
 * @author Piotr Krzyminski
 */
public class MainActivity extends Activity {

    public static final String RESULT_KEY = "result";

    private static final Logger LOG = LoggerFactory.getLogger(MainActivity.class);

    private CalculatorService calculatorService = new DefaultCalculatorService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Sends result of calculation as intent to result activity sceen.
     *
     * @param view clicked button that should perform action.
     */
    public void sendResult(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(RESULT_KEY, getCalculationResult());
        startActivity(intent);
    }

    /**
     * Get values written to two text inputs, covert it to integers and perform addition.
     *
     * @return result of add two integer values.
     */
    private int getCalculationResult() {
        EditText inputOne = findViewById(R.id.input1);
        EditText inputTwo = findViewById(R.id.input2);

        int valueOne = Integer.valueOf(inputOne.getText().toString());
        int valueTwo = Integer.valueOf(inputTwo.getText().toString());

        LOG.debug("Performing calcuation {} + {}", valueOne, valueTwo);

        return calculatorService.add(valueOne, valueTwo);
    }

}
