package codingtest.app;

import io.dropwizard.Application;
import io.dropwizard.jetty.setup.ServletEnvironment;
import io.dropwizard.setup.Environment;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import codingtest.dbi.DBIModule;
import codingtest.resources.ExpensesResource;
import codingtest.resources.ResourcesModule;
import codingtest.service.ServicesModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ExpensesApplication extends Application<ExpensesConfiguration> {

    @Override
    public void run(ExpensesConfiguration configuration,
                Environment environment) {

        final Injector injector = createInjector(configuration, environment);

        environment.jersey().register(injector.getInstance(ExpensesResource.class));

        configureCors(environment.servlets());
    }

    private Injector createInjector(ExpensesConfiguration configuration, Environment environment) {
        return Guice.createInjector(new ResourcesModule(), new DBIModule(environment, configuration), new ServicesModule(configuration));
    }

    private void configureCors(ServletEnvironment servletEnvironment) {
        final Dynamic filter = servletEnvironment.addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
    }

    public static void main(String[] args) throws Exception {
        new ExpensesApplication().run(args);
    }
}