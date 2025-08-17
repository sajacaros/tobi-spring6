package kr.study.spring6;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.study.spring6.type.Currency;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
public class PaymentService {
    // 주문번호, 외국 통화 종류, 외국 통화 기준 결제 금액
    private Payment prepare(Long orderId, Currency currency, BigDecimal amount) throws IOException {
        //  적용 환율
        // https://open.er-api.com/v6/latest/{기준통화} 이용
        URL url = new URL("https://open.er-api.com/v6/latest/"+Currency.KRW.name());
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String response;
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            response = bufferedReader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            log.warn("usd read error : ", e);
            throw e;
        }

        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData = mapper.readValue(response, ExRateData.class);
        BigDecimal exRate = exRateData.rates().get(currency.name());

        //  원화 환산 금액
        BigDecimal convertedAmount = amount.multiply(exRate);
        //  원화 환산 금액 유효시간
        LocalDateTime validUntil = LocalDateTime.now().plusDays(1L);

        return Payment.builder()
                .orderId(orderId)
                .amountInUsd(convertedAmount)
                .currency(currency)
                .exRate(exRate)
                .convertedAmount(convertedAmount)
                .validUntil(validUntil)
                .build();
    }

    public static void main(String[] args) throws IOException {
        Long orderId = 1L;
        Currency currency = Currency.USD;
        BigDecimal amount = new BigDecimal("10.3");

        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(orderId, currency, amount);
        System.out.println(payment);
    }
}
