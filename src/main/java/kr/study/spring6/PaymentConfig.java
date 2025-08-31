package kr.study.spring6;

import kr.study.spring6.api.ApiTemplate;
import kr.study.spring6.exrate.CachedExRateProvider;
import kr.study.spring6.payment.ExRateProvider;
import kr.study.spring6.exrate.WebApiExRateProvider;
import kr.study.spring6.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate();
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
//        return new SimpleExRateProvider();
        return new WebApiExRateProvider(apiTemplate());
    }
}
