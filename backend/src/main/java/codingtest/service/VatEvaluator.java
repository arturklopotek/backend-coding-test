package codingtest.service;

import java.math.BigDecimal;

public interface VatEvaluator {

    /**
     * Evaluates VAT value for the given amount (with VAT included). The
     * evaluation method depends on concrete implementation.
     * 
     * @param amountIncludingVat
     *            total amount (including VAT)
     * @return VAT value included in the supplied amount
     */
    BigDecimal evaluateVat(BigDecimal amountIncludingVat);
}
