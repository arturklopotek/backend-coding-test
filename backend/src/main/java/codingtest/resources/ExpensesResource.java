package codingtest.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import codingtest.dto.ExpenseItem;
import codingtest.service.ExpenseManager;

import com.google.inject.Inject;

@Path("/expenses")
@Produces(MediaType.APPLICATION_JSON)
public class ExpensesResource {

    private final ExpenseManager manager;

    @Inject
    public ExpensesResource(ExpenseManager manager) {
        this.manager = manager;
    }

    @GET
    public List<ExpenseItem> getExpenses() {
        return manager.getExpenses();
    }

    @POST
    public ExpenseItem createExpense(@Valid ExpenseItem expense) {
        final ExpenseItem savedExpense = manager.saveExpense(expense);
        return savedExpense;
    }
}