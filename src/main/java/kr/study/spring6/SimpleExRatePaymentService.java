package kr.study.spring6;

import kr.study.spring6.type.Currency;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;

@Slf4j
public class SimpleExRatePaymentService extends PaymentService {
    @Override
    BigDecimal getExRate(Currency currency) throws IOException {

        return BigDecimal.valueOf(1300);

    }
}
