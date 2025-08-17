package kr.study.spring6.exrate;

import kr.study.spring6.type.Currency;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExRateProvider {
    BigDecimal getExRate(Currency currency) throws IOException;
}
