package android.math.uni.lodz.pl.calculator.activities;

import android.app.Activity;
import android.math.uni.lodz.pl.calculator.R;
import android.math.uni.lodz.pl.calculator.dao.HistoryDao;
import android.math.uni.lodz.pl.calculator.dao.impl.DefaultHistoryDao;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

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

        final HistoryDao historyDao = new DefaultHistoryDao(this);

        String result;
        final List<String> historyList = historyDao.getAllHistory();
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
    private String getHistoryList(final List<String> historyList) {
        StringBuilder sb = new StringBuilder();
        historyList.forEach(op -> sb.append(op).append('\n'));

        return sb.toString();
    }
}
