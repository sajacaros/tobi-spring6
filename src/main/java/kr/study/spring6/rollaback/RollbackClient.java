package kr.study.spring6.rollaback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class RollbackClient {
    public static void main(String[] args) {
        ConfigurableApplicationContext beanFactory = SpringApplication.run(RollbackClient.class, args);
        ReviewService reviewService = beanFactory.getBean(ReviewService.class);

        try {
            log.info("기본 런타임 예외 처리");
            reviewService.saveReview1();
            log.info("Review1 : {}", reviewService.findAll());
        } catch (Exception e) { // 롤백 예외
            e.printStackTrace();
        }

        log.info("----------------------");
        log.info("noRollbackFor 처리 처리");
        reviewService.saveReview2();
        log.info("Review2 : {}", reviewService.findAll());

        log.info("----------------------");
        log.info("REQUIRES_NEW 처리 처리");
        reviewService.saveReview3();
        log.info("Review3 : {}", reviewService.findAll());
    }
}
