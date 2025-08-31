package kr.study.spring6.exrate;

import kr.study.spring6.api.ApiTemplate;
import kr.study.spring6.api.ErApiExRateExtractor;
import kr.study.spring6.api.HttpClientApiExecutor;
import kr.study.spring6.api.SimpleApiExecutor;
import kr.study.spring6.payment.ExRateProvider;
import kr.study.spring6.type.Currency;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class WebApiExRateProvider implements ExRateProvider {
    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(Currency currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency.name();
        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
    }
}
