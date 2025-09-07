package kr.study.spring6;

import kr.study.spring6.configure.DataConfig;
import kr.study.spring6.data.OrderRepository;
import kr.study.spring6.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Slf4j
public class DataClient {
    public static void main(String[] args) throws InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        new TransactionTemplate(transactionManager).execute(new TransactionCallback<Order>() {
            @Override
            public @Nullable Order doInTransaction(TransactionStatus status) {
                Order order = new Order("100", BigDecimal.TEN);
                repository.save(order);

                log.info("save order, {}", order);

                Order order2 = new Order("100", BigDecimal.TEN);
                repository.save(order2); // ConstraintViolationException 발생
                return null;
            }
        });
    }
}
