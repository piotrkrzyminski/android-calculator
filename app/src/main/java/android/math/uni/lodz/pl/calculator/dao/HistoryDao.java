package android.math.uni.lodz.pl.calculator.dao;

import java.util.List;

/**
 * Data access object for calculator history.
 *
 * @author Piotr Krzyminski
 */
public interface HistoryDao {

    String TABLE_NAME = "history";

    interface Columns {
        String HISTORY_ID = "id";
        String HISTORY_ENTRY = "expression";
    }

    /**
     * Inserts new history entry to database.
     *
     * @param entry history entry to insert.
     */
    void insert(String entry);

    /**
     * Get all saved history entries from database.
     *
     * @return list of all history entries.
     */
    List<String> getAllHistory();

    /**
     * Deleted all history expressions from table
     */
    void deleteAll();
}
