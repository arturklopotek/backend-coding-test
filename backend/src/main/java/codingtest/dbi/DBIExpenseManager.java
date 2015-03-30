package codingtest.dbi;

import static codingtest.dto.ExpenseItemBuilder.expenseItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.skife.jdbi.v2.DBI;

import codingtest.dto.ExpenseItem;
import codingtest.model.Expense;
import codingtest.service.ExpenseManager;
import codingtest.service.VatEvaluator;

import com.google.inject.Inject;

/**
 * The implementation of {@link ExpenseManager} that uses JDBI as it's
 * persistence API.
 *
 */
public class DBIExpenseManager implements ExpenseManager {

    private final ExpenseDAO dao;
    private final VatEvaluator vatEvaluator;

    @Inject
    public DBIExpenseManager(DBI dbi, VatEvaluator vatEvaluator) {
        this.dao = dbi.onDemand(ExpenseDAO.class);
        this.vatEvaluator = vatEvaluator;
    }

    @Override
    public List<ExpenseItem> getExpenses() {
        return dao.getAll()
                    .stream()
                    .map(this::mapExpense)
                    .collect(Collectors.toList());
    }

    @Override
    public ExpenseItem saveExpense(ExpenseItem expense) {
        final long savedId = dao.insert(expense.getDate(), expense.getAmount(), expense.getReason());

        final Expense savedExpense = dao.getById(savedId);

        return mapExpense(savedExpense);
    }

    private ExpenseItem mapExpense(Expense expense) {
        final BigDecimal vat = vatEvaluator.evaluateVat(expense.getAmount());
        return expenseItem().forExpense(expense).vat(vat).build();
    }
}
