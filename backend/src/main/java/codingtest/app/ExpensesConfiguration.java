package codingtest.app;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import codingtest.service.LinearVatEvaluator;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpensesConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty
    private LinearVatEvaluator.Config vat = new LinearVatEvaluator.Config();

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public LinearVatEvaluator.Config getVatEvaluatorConfig() {
        return vat;
    }
}