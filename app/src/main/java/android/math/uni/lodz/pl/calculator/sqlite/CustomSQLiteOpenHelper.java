package android.math.uni.lodz.pl.calculator.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.math.uni.lodz.pl.calculator.dao.HistoryDao;

/**
 * Custom implementation that defines sql script for creating table.
 * It creates database with name "CalculatorDB with empty table named "history".
 *
 * @author Piotr Krzyminski
 */
public class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "CalculatorDB.db";

    /**
     * Constructor that pass database name and version.
     *
     * @param context context.
     */
    public CustomSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HISTORY_TABLE = "CREATE TABLE " + HistoryDao.TABLE_NAME +
                "( " + HistoryDao.Columns.HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + HistoryDao.Columns.HISTORY_ENTRY + " TEXT)";
        db.execSQL(CREATE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HistoryDao.TABLE_NAME);
        this.onCreate(db);
    }

    /**
     * Inserts new history entry to table.
     *
     * @param entry history entry to insert.
     */
    public void insertHistory(String entry) {

    }
}
