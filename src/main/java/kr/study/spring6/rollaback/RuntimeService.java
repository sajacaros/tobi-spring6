package kr.study.spring6.rollaback;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RuntimeService {

    @Transactional
    public void throwRuntimeException1() {
        throw new RuntimeException("런타임 예외 발생");
    }

    @Transactional(noRollbackFor = RuntimeException.class)
    public void throwRuntimeException2() {
        throw new RuntimeException("런타임 예외 발생");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void throwRuntimeException3() {
        throw new RuntimeException("런타임 예외 발생");
    }
}
