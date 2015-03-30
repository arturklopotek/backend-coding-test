package codingtest.service;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import io.dropwizard.testing.FixtureHelpers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.junit.Test;

import codingtest.dto.ExpenseItem;

public class ExpensesAcceptanceTest extends AcceptanceTestBase {

    @Test
    public void GET_expenses_shouldRetrieveSavedEntities() throws IOException {
        // given
        request("/expenses").post(jsonEntity("fixtures/expense-item-1.json"));
        request("/expenses").post(jsonEntity("fixtures/expense-item-2.json"));

        
        // when
        Response response = request("/expenses").get();

        // then
        assertThat(response.getStatus(), is(200));
        
        List<ExpenseItem> items = response.readEntity(new GenericType<List<ExpenseItem>>(){});
        assertThat(items.get(0).getAmount(), is(new BigDecimal("120.00")));
        assertThat(items.get(1).getAmount(), is(new BigDecimal("15.50")));
    }

    @Test
    public void POST_expenses_shouldSaveAndReturnSavedEntity() throws IOException {
        // given
        Entity<?> entity = jsonEntity("fixtures/expense-item-1.json");

        // when
        Response response = request("/expenses").post(entity);

        // then
        assertThat(response.getStatus(), is(200));

        ExpenseItem item = response.readEntity(ExpenseItem.class);
        assertThat(item.getId(), is(1L));
        assertThat(item.getDate(), is(DateTime.parse("2015-03-02")));
        assertThat(item.getAmount(), is(new BigDecimal("120.00")));
        assertThat(item.getReason(), is("New hard drive"));
        assertThat(item.getVat(), is(new BigDecimal("20.00")));
    }

    @Test
    public void POST_expenses_shouldFailWithCode422_whenSavingInvalidEntity() throws IOException {
        // given
        Entity<?> entity = jsonEntity("fixtures/expense-item-invalid.json");

        // when
        Response response = request("/expenses").post(entity);

        // then
        assertThat(response.getStatus(), is(422));
    }

    @Test
    public void POST_expenses_shouldFailWithCode400_whenPassedMalformedJson() throws IOException {
        // given
        Entity<?> entity = jsonEntity("fixtures/expense-item-malformed.json");

        // when
        Response response = request("/expenses").post(entity);

        // then
        assertThat(response.getStatus(), is(400));
    }

    private Entity<?> jsonEntity(String fixturePath) {
        return Entity.json(FixtureHelpers.fixture(fixturePath));
    }

}
