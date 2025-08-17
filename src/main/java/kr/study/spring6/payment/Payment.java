package kr.study.spring6.payment;

import kr.study.spring6.type.Currency;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record Payment(
        Long orderId,
        BigDecimal amount,
        Currency currency,
        BigDecimal exRate,
        BigDecimal convertedAmount,
        LocalDateTime validUntil
) {
}
