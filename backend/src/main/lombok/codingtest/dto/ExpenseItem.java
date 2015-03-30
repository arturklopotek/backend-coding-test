package codingtest.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Builder
public class ExpenseItem {

    @JsonProperty
    private Long id;

    @NotNull
    private DateTime date;

    @NotNull
    private BigDecimal amount;

    private BigDecimal vat;

    @NotBlank
    private String reason;

    public ExpenseItem() {
    }

    ExpenseItem(Long id, DateTime date, BigDecimal amount, BigDecimal vat, String reason) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.vat = vat;
        this.reason = reason;
    }
}