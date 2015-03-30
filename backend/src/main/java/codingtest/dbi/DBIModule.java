package codingtest.dbi;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;

import org.skife.jdbi.v2.DBI;

import codingtest.app.ExpensesConfiguration;

import com.google.inject.Binder;
import com.google.inject.Module;

public class DBIModule implements Module {

    private final DBI jdbi;

    public DBIModule(Environment environment, ExpensesConfiguration configuration) {
        final DBIFactory factory = new DBIFactory();
        this.jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(DBI.class).toInstance(jdbi);
    }

}
