package android.math.uni.lodz.pl.calculator.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.math.uni.lodz.pl.calculator.dao.HistoryDao;
import android.math.uni.lodz.pl.calculator.sqlite.CustomSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of {@link HistoryDao}.
 *
 * @author Piotr Krzyminski
 */
public class DefaultHistoryDao implements HistoryDao {

    private CustomSQLiteOpenHelper dbHelper;

    public DefaultHistoryDao(Context context) {
        dbHelper = new CustomSQLiteOpenHelper(context);
    }

    @Override
    public void insert(String entry) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.HISTORY_ENTRY, entry);

        dbHelper.getWritableDatabase().insert(HistoryDao.TABLE_NAME, null, contentValues);
    }

    @Override
    public List<String> getAllHistory() {
        Cursor cursor = dbHelper.getReadableDatabase().query(HistoryDao.TABLE_NAME, new String[]
                        {Columns.HISTORY_ID, Columns.HISTORY_ENTRY},
                null, null, null, null, null);

        final List<String> historyList = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                historyList.add(getExpressionFromCursor(cursor));
            }
        }

        return historyList;
    }

    @Override
    public void deleteAll() {
        dbHelper.getWritableDatabase().delete(HistoryDao.TABLE_NAME, null, null);
    }

    /**
     * Get string expression from cursor.
     *
     * @param cursor cursor.
     * @return string value received from cursor.
     */
    private String getExpressionFromCursor(final Cursor cursor) {
        int expressionColumnId = cursor.getColumnIndex(Columns.HISTORY_ENTRY);
        return cursor.getString(expressionColumnId);
    }
}
