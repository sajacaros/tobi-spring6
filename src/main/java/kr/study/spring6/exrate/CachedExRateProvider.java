package kr.study.spring6.exrate;

import kr.study.spring6.payment.ExRateProvider;
import kr.study.spring6.type.Currency;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CachedExRateProvider implements ExRateProvider {
    private final ExRateProvider target;

    Map<Currency, ExRateWithValidTime> exRateWithValidTimeMap = new HashMap<>();

    public CachedExRateProvider(ExRateProvider exRateProvider) {
        this.target = exRateProvider;
    }

    @Override
    public BigDecimal getExRate(Currency currency) throws IOException {
        ExRateWithValidTime exRateWithValidTime = exRateWithValidTimeMap.get(currency);
        if (exRateWithValidTime != null && exRateWithValidTime.isValid()) {
            System.out.println("캐시 ExRate : " + exRateWithValidTime.getExRate());
            return exRateWithValidTime.getExRate();
        } else {
            BigDecimal exRate = this.target.getExRate(currency);
            exRateWithValidTimeMap.put(
                    currency,
                    ExRateWithValidTime.builder()
                            .exRate(exRate)
                            .validDateTime(LocalDateTime.now().plusSeconds(5))
                            .build()
            );
            return exRate;
        }
    }
}
