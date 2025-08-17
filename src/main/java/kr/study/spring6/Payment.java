package kr.study.spring6;

import kr.study.spring6.type.Currency;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record Payment(
        Long orderId,
        BigDecimal amountInUsd,
        Currency currency,
        BigDecimal exRate,
        BigDecimal convertedAmount,
        LocalDateTime validUntil
) {
}
