package kr.study.spring6.exrate;

import kr.study.spring6.payment.ExRateProvider;
import kr.study.spring6.type.Currency;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;

public class RestTempalteExRateProvider implements ExRateProvider {
    private final RestTemplate restTemplate;

    public RestTempalteExRateProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getExRate(Currency currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency.name();
        return Objects.requireNonNull(restTemplate.getForObject(url, ExRateData.class))
                .rates().get(Currency.KRW.name());
    }
}
