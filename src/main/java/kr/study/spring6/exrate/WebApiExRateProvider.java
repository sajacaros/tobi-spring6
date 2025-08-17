package kr.study.spring6.exrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.study.spring6.payment.ExRateProvider;
import kr.study.spring6.type.Currency;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

@Slf4j
public class WebApiExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(Currency currency) throws IOException {

        //  적용 환율
        // https://open.er-api.com/v6/latest/{기준통화} 이용
        URL url = new URL("https://open.er-api.com/v6/latest/" + Currency.USD.name());
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String response;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            response = bufferedReader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            log.warn("usd read error : ", e);
            throw e;
        }

        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData = mapper.readValue(response, ExRateData.class);

        return exRateData.rates().get(currency.name());

    }
}
