package codingtest.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.joda.time.DateTime;

@Getter
@AllArgsConstructor
public class Expense {

    private Long id;
    private DateTime date;
    private BigDecimal amount;
    private String reason;
}