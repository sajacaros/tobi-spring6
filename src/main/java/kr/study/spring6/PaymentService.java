package kr.study.spring6;

import kr.study.spring6.type.Currency;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
public abstract class PaymentService {
    // 주문번호, 외국 통화 종류, 외국 통화 기준 결제 금액
    public Payment prepare(Long orderId, Currency currency, BigDecimal amount) throws IOException {
        BigDecimal exRate = getExRate(currency);

        //  원화 환산 금액
        BigDecimal convertedAmount = amount.multiply(exRate);
        //  원화 환산 금액 유효시간
        LocalDateTime validUntil = LocalDateTime.now().plusDays(1L);

        return Payment.builder()
                .orderId(orderId)
                .amount(convertedAmount)
                .currency(currency)
                .exRate(exRate)
                .convertedAmount(convertedAmount)
                .validUntil(validUntil)
                .build();
    }

    abstract BigDecimal getExRate(Currency currency) throws IOException;
}
