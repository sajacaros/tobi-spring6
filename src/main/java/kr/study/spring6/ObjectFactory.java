package kr.study.spring6;

import kr.study.spring6.exrate.CachedExRateProvider;
import kr.study.spring6.payment.ExRateProvider;
import kr.study.spring6.exrate.WebApiExRateProvider;
import kr.study.spring6.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
//        return new SimpleExRateProvider();
        return new WebApiExRateProvider();
    }


}
