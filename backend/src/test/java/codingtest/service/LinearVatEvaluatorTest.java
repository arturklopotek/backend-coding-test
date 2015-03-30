package codingtest.service;

import static org.hamcrest.CoreMatchers.is;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import codingtest.service.LinearVatEvaluator.Config;

public class LinearVatEvaluatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void evaluateVat_shouldFail_whenPassedInvalidAmount() {
        //given
        LinearVatEvaluator evaluator = createVatEvaluator("0.20");

        // when
        evaluator.evaluateVat(new BigDecimal("10.123"));
    }

    @Test
    public void evaluateVat_shouldEvaluateCorrectVatAmount() {
        // given
        LinearVatEvaluator evaluator = createVatEvaluator("0.20");

        // when
        BigDecimal vatAmount = evaluator.evaluateVat(new BigDecimal("24"));

        // then
        Assert.assertThat(vatAmount, is(new BigDecimal("4.00")));
    }

    private static LinearVatEvaluator createVatEvaluator(String rate) {
        final Config config = new LinearVatEvaluator.Config();
        config.setRate(new BigDecimal(rate));
        return new LinearVatEvaluator(config);
    }
}
