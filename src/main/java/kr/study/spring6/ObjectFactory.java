package kr.study.spring6;

import kr.study.spring6.type.Currency;

import java.math.BigDecimal;

public class ObjectFactory {
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    private ExRateProvider exRateProvider() {
//        return new SimpleExRateProvider();
        return new WebApiExRateProvider();
    }


}
