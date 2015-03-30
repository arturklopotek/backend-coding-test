package codingtest.service;

import io.dropwizard.jackson.Jackson;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import codingtest.app.ExpensesApplication;
import codingtest.app.ExpensesConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AcceptanceTestBase {

    protected static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private static SchemaHelper schemaHelper;

    protected final Client client = JerseyClientBuilder.newClient();

    @ClassRule
    public static final DropwizardAppRule<ExpensesConfiguration> RULE =
                new DropwizardAppRule<ExpensesConfiguration>(ExpensesApplication.class, ResourceHelpers.resourceFilePath("app-test.yml"));

    @BeforeClass
    public static void initDatabase() {
        Environment environment = RULE.getEnvironment();
        ExpensesConfiguration configuration = RULE.getConfiguration();

        final DBIFactory factory = new DBIFactory();
        DBI dbi = factory.build(environment, configuration.getDataSourceFactory(), "test-db");
        schemaHelper = dbi.onDemand(SchemaHelper.class);

        schemaHelper.createExpensesTable();
    }

    @Before
    public void truncateDatabase() {
        schemaHelper.truncateExpensesTable();
    }

    protected Builder request(String path) {
        final String requestUri = String.format("http://localhost:%d%s", RULE.getLocalPort(), path);
        return client.target(requestUri).request();
    }

    public interface SchemaHelper {

        @SqlUpdate("CREATE TABLE expenses ("
                    + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "date DATE NOT NULL,"
                    + "amount DECIMAL(12, 2) NOT NULL,"
                    + "reason VARCHAR(200) NOT NULL)")
        public void createExpensesTable();

        @SqlUpdate("TRUNCATE TABLE expenses")
        public void truncateExpensesTable();
    }
}
