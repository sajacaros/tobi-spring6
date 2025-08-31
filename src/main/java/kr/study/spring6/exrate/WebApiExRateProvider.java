package kr.study.spring6.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.study.spring6.payment.ExRateProvider;
import kr.study.spring6.type.Currency;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

@Slf4j
public class WebApiExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(Currency currency) {

        //  적용 환율
        // https://open.er-api.com/v6/latest/{기준통화} 이용
        URI uri;
        try {
            uri = new URI("https://open.er-api.com/v6/latest/" + currency.name());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        uri.toURL().openConnection().getInputStream()
                )
        )) {
            response = bufferedReader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData;
        try {
            exRateData = mapper.readValue(response, ExRateData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return exRateData.rates().get(Currency.KRW.name());
    }
}
