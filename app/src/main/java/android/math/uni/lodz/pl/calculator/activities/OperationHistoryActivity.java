package android.math.uni.lodz.pl.calculator.activities;

import android.app.Activity;
import android.math.uni.lodz.pl.calculator.R;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Activity displays list of all performed operations.
 *
 * @author Piotr Krzyminski
 */
public class OperationHistoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String result;
        final ArrayList<String> historyList = getIntent().getStringArrayListExtra(MainActivity.OPERATION_HISTORY_LIST);
        if (historyList == null || historyList.isEmpty()) {
            result = (getResources().getString(R.string.no_history_message));
        } else {
            result = getHistoryList(historyList);
        }

        TextView historyListTextView = findViewById(R.id.history_list);
        historyListTextView.setText(result);
    }

    /**
     * Creates string value with listed all performed operations.
     *
     * @param historyList list of all performed operations.
     * @return concatenated value with all entries from operations history array.
     */
    private String getHistoryList(final ArrayList<String> historyList) {
        StringBuilder sb = new StringBuilder();
        historyList.forEach(op -> sb.append(op).append('\n'));

        return sb.toString();
    }
}
