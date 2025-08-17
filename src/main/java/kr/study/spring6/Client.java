package kr.study.spring6;

import kr.study.spring6.payment.Payment;
import kr.study.spring6.payment.PaymentService;
import kr.study.spring6.type.Currency;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Long orderId = 1L;
        Currency currency = Currency.USD;
        BigDecimal amount = new BigDecimal("10.3");

        Payment payment = paymentService.prepare(orderId, currency, amount);
        System.out.println(payment);

        TimeUnit.SECONDS.sleep(3);
        Payment payment2 = paymentService.prepare(orderId, currency, amount);
        System.out.println(payment2);

        TimeUnit.SECONDS.sleep(5);
        Payment payment3 = paymentService.prepare(orderId, currency, amount);
        System.out.println(payment3);
    }
}
