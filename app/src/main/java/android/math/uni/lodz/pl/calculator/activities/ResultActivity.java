package android.math.uni.lodz.pl.calculator.activities;

import android.app.Activity;
import android.content.Intent;
import android.math.uni.lodz.pl.calculator.R;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Activity displaying result of operation from previous activity.
 *
 * @author Piotr Krzyminski
 */
public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        final int result = intent.getIntExtra(MainActivity.RESULT_KEY, 0);
        final TextView resultTextView = findViewById(R.id.result_text_view);
        resultTextView.setText(getString(R.string.result_description, String.valueOf(result)));
    }
}
