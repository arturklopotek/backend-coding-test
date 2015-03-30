package codingtest.resources;

import codingtest.dbi.DBIExpenseManager;
import codingtest.service.ExpenseManager;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;

public class ResourcesModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(ExpenseManager.class).to(DBIExpenseManager.class).in(Singleton.class);

    }
}
