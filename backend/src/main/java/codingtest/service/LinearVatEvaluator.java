package codingtest.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * The implementation of {@link VatEvaluator} that calculates VAT value using
 * the given linear rate.
 *
 */
public class LinearVatEvaluator implements VatEvaluator {

    private final Config config;

    public LinearVatEvaluator(Config config) {
        this.config = config;
    }

    @Override
    public BigDecimal evaluateVat(BigDecimal amountIncludingVat) {
        if (amountIncludingVat.scale() > 2) {
            throw new IllegalArgumentException("Supplied amount has more than 2 decimal places");
        }

        final BigDecimal amount = amountIncludingVat.setScale(2);
        final BigDecimal divisor = BigDecimal.ONE.add(config.getRate());

        return amount.subtract(amount.divide(divisor, RoundingMode.HALF_UP));
    }

    public static final class Config {

        @Valid
        @NotNull
        private BigDecimal rate;

        public BigDecimal getRate() {
            return rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }
    }

}
