package codingtest.service;

import java.util.List;

import codingtest.dto.ExpenseItem;

public interface ExpenseManager {

    /**
     * Retrieves the list of all expense items.
     * 
     * @return the list of {@link ExpenseItem} objects
     */
    List<ExpenseItem> getExpenses();

    /**
     * Persists the given {@link ExpenseItem}.
     * 
     * @param expense
     *            object to be stored
     * @return the item stored
     */
    ExpenseItem saveExpense(ExpenseItem expense);
}
