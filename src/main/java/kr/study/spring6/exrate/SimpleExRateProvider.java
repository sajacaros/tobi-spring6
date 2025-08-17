package kr.study.spring6.exrate;

import kr.study.spring6.payment.ExRateProvider;
import kr.study.spring6.type.Currency;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;

@Slf4j
public class SimpleExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(Currency currency) throws IOException {
        if (currency.equals(Currency.USD)) {
            return BigDecimal.valueOf(1300);
        }

        throw new IllegalArgumentException("Unsupported currency: " + currency);
    }
}
