package android.math.uni.lodz.pl.calculator.activities;

import android.app.Activity;
import android.content.Intent;
import android.math.uni.lodz.pl.calculator.R;
import android.math.uni.lodz.pl.calculator.dao.HistoryDao;
import android.math.uni.lodz.pl.calculator.dao.impl.DefaultHistoryDao;
import android.math.uni.lodz.pl.calculator.services.CalculatorService;
import android.math.uni.lodz.pl.calculator.services.impl.DefaultCalculatorService;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Main activity. Calculates two numbers and send result to other activity.
 *
 * @author Piotr Krzyminski
 */
public class MainActivity extends Activity {

    public static final String OPERATION_HISTORY_LIST = "historyList";

    private static final Logger LOG = LoggerFactory.getLogger(MainActivity.class);

    private CalculatorService calculatorService;

    private HistoryDao historyDao;

    private static List<String> history = new ArrayList<>();

    /**
     * Load view of activity on create.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorService = new DefaultCalculatorService();
        historyDao = new DefaultHistoryDao(this);
    }

    /**
     * Action assigned to calculator buttons. It gets text from button and append text in text view.
     * If previous expression ended with error then clear error message from screen first to prevent
     * next errors.
     *
     * @param view button.
     */
    public void addValueToView(View view) {
        final Button button = (Button) view;
        final String value = button.getText().toString();
        final TextView resultView = findViewById(R.id.result_text_view);

        // if previous expression failed then clear error message from screen first.
        if (resultView.getText().equals(getResources().getString(R.string.wrong_expression_message))) {
            resultView.setText("");
        }

        final String resultViewText = resultView.getText().toString();
        resultView.setText(resultViewText + value);
    }

    /**
     * Get string math expression from text view and calculate result.
     * If expression cannot be proceed then display error message on screen. Otherwise add
     * expression to calculation history list and display result.
     *
     * @param view result button ("=").
     */
    public void calculateResult(View view) {
        final TextView resultView = findViewById(R.id.result_text_view);
        final String expression = resultView.getText().toString();
        LOG.debug("Expression to parse: {}", expression);

        String result = getResources().getString(R.string.wrong_expression_message);
        try {
            result = formatDoubleResult(calculatorService.parseExpression(expression));
            LOG.debug("Expression parsed successfully. Result equals: " + result);
            //history.add(expression + " = " + result);
            historyDao.insert(expression + " = " + result);
        } catch (IllegalArgumentException e) {
            LOG.debug("Expression cannot be parsed due to error: " + e.getMessage());
        }

        resultView.setText(result);
    }

    /**
     * Clear text view from any values.
     *
     * @param view text view.
     */
    public void clearScreen(View view) {
        final TextView textView = findViewById(R.id.result_text_view);
        textView.setText("");
    }

    /**
     * Clears all values from history list.
     */
    public void clearHistory(View view) {
        //history = new ArrayList<>();
        historyDao.deleteAll();
    }

    /**
     * Send list of history to other activity and display it.
     */
    public void displayHistory(View view) {
        Intent intent = new Intent(this, OperationHistoryActivity.class);
        //intent.putStringArrayListExtra(OPERATION_HISTORY_LIST, (ArrayList<String>) history);
        startActivity(intent);
    }

    /**
     * Formats double value to String. It is hide zero as last digit.
     *
     * @param result double value.
     * @return formatted string value.
     */
    private String formatDoubleResult(final double result) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.#########", otherSymbols);

        return df.format(result);
    }
}
