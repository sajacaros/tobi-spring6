package kr.study.spring6.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.study.spring6.api.ApiExecutor;
import kr.study.spring6.api.ErApiExRateExtractor;
import kr.study.spring6.api.ExRateExtractor;
import kr.study.spring6.api.SimpleApiExecutor;
import kr.study.spring6.payment.ExRateProvider;
import kr.study.spring6.type.Currency;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class WebApiExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(Currency currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency.name();
        return runApiForExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
//        return runApiForExRate(url, new SimpleApiExecutor(), response -> {
//            ObjectMapper mapper = new ObjectMapper();
//            ExRateData exRateData = mapper.readValue(response, ExRateData.class);
//            return exRateData.rates().get(Currency.KRW.name());
//        });
    }

    private BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
