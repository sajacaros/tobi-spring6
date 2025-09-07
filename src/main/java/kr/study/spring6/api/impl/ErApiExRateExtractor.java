package kr.study.spring6.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.study.spring6.api.ExRateExtractor;
import kr.study.spring6.exrate.ExRateData;
import kr.study.spring6.type.Currency;

import java.math.BigDecimal;

public class ErApiExRateExtractor implements ExRateExtractor {
    @Override
    public BigDecimal extract(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData = mapper.readValue(response, ExRateData.class);
        return exRateData.rates().get(Currency.KRW.name());
    }
}
