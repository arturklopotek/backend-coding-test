package codingtest.dbi;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import codingtest.model.Expense;

@RegisterMapper(ExpenseDAO.ExpenseMapper.class)
public interface ExpenseDAO {

    @SqlUpdate("insert into expenses (date, amount, reason) values (:date, :amount, :reason)")
    @GetGeneratedKeys
    long insert(@Bind("date") DateTime date, @Bind("amount") BigDecimal amount, @Bind("reason") String reason);

    @SqlQuery("select * from expenses order by date desc")
    List<Expense> getAll();

    @SqlQuery("select * from expenses where id = :id")
    Expense getById(@Bind("id") long id);
    
    public class ExpenseMapper implements ResultSetMapper<Expense>
    {
        public Expense map(int index, ResultSet r, StatementContext ctx) throws SQLException
        {
            return new Expense(r.getLong("id"), new DateTime(r.getDate("date").getTime()),
                        r.getBigDecimal("amount"), r.getString("reason"));
        }
    }

}
