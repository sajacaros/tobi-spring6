package kr.study.spring6;

import kr.study.spring6.type.Currency;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Long orderId = 1L;
        Currency currency = Currency.KRW;
        BigDecimal amount = new BigDecimal("10.3");
        Payment payment = paymentService.prepare(orderId, currency, amount);
        System.out.println(payment);
    }
}
