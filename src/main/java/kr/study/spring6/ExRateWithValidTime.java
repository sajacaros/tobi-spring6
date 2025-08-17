package kr.study.spring6;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class ExRateWithValidTime {
    private BigDecimal exRate;
    private LocalDateTime validDateTime;

    public boolean isValid() {
        return LocalDateTime.now().isBefore(validDateTime);
    }
}
