package codingtest.service;

import codingtest.app.ExpensesConfiguration;
import codingtest.service.LinearVatEvaluator.Config;

import com.google.inject.Binder;
import com.google.inject.Module;

public class ServicesModule implements Module {

    private final ExpensesConfiguration configuration;

    public ServicesModule(ExpensesConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void configure(Binder binder) {
        final Config vatEvaluatorConfig = configuration.getVatEvaluatorConfig();
        binder.bind(VatEvaluator.class).toInstance(new LinearVatEvaluator(vatEvaluatorConfig));
    }

}
