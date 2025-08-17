package kr.study.spring6;

import kr.study.spring6.type.Currency;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        Long orderId = 1L;
        Currency currency = Currency.KRW;
        BigDecimal amount = new BigDecimal("10.3");

//        PaymentService paymentService = new WebApiExRatePaymentService();
        PaymentService paymentService = new SimpleExRatePaymentService();
        Payment payment = paymentService.prepare(orderId, currency, amount);
        System.out.println(payment);
    }
}
