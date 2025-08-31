package kr.study.spring6.exrate;

import kr.study.spring6.api.ApiTemplate;
import kr.study.spring6.payment.ExRateProvider;
import kr.study.spring6.type.Currency;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class WebApiExRateProvider implements ExRateProvider {
    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(Currency currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency.name();
        return apiTemplate.getExRate(url);
    }
}
