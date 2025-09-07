package kr.study.spring6.rollaback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ReviewService {
    @Autowired
    private ReviewRepository repository;
    @Autowired
    private RuntimeService runtimeService;

    @Transactional
    public void saveReview1() {
        try {
            repository.save(new Review("리뷰"));
            runtimeService.throwRuntimeException1();
        } catch (RuntimeException e) {
            log.info("예외 잡음~~");
        }
    }

    @Transactional
    public void saveReview2() {
        try {
            repository.save(new Review("리뷰"));
            runtimeService.throwRuntimeException2();
        } catch (RuntimeException e) {
            log.info("예외 잡음~~");
        }
    }

    @Transactional
    public void saveReview3() {
        try {
            repository.save(new Review("리뷰"));
            runtimeService.throwRuntimeException3();
        } catch (RuntimeException e) {
            log.info("예외 잡음~~");
        }
    }

    @Transactional(readOnly = true)
    public List<Review> findAll() {
        return repository.findAll();
    }
}
