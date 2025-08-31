package kr.study.spring6;

import kr.study.spring6.exrate.CachedExRateProvider;
import kr.study.spring6.exrate.RestTempalteExRateProvider;
import kr.study.spring6.payment.ExRateProvider;
import kr.study.spring6.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

//    @Bean
//    public ApiTemplate apiTemplate() {
//        return new ApiTemplate();
//    }

//    @Bean
//    public ExRateProvider exRateProvider() {
//        return new WebApiExRateProvider(apiTemplate());
//    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new JdkClientHttpRequestFactory());
//        return new RestTemplate();
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTempalteExRateProvider(restTemplate());
    }
}
